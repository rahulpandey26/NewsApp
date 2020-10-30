package com.medibank.shop.home.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medibank.shop.R
import com.medibank.shop.common.util.Constants
import com.medibank.shop.common.util.NetworkUtil
import com.medibank.shop.database.NewsViewModel
import com.medibank.shop.home.adapter.HeadlinesListAdapter
import com.medibank.shop.home.model.Article
import com.medibank.shop.home.model.NewsHeadlineResponse
import com.medibank.shop.home.ui.activity.NewsDetailsActivity
import com.medibank.shop.home.util.HomeFactory
import com.medibank.shop.home.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HeadlinesFragment : Fragment(), HeadlinesListAdapter.OnNewsHeadlinesListener {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var mNewsHeadlineResponse : NewsHeadlineResponse
    private lateinit var mAdapter: HeadlinesListAdapter
    private lateinit var mNewsViewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        initializeViews(rootView)
        return rootView
    }

    private fun initializeViews(rootView: View?) {
        mRecyclerView = rootView!!.findViewById(R.id.recycler_view)
        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        mNewsViewModel.allNews?.observe(viewLifecycleOwner,
            Observer<List<Article?>?> {
            })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getNewsHeadlinesList()
    }

    override fun onResume() {
        super.onResume()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    private fun getNewsHeadlinesList() {
        if(!NetworkUtil.isNetworkAvailable(requireContext())){
            Toast.makeText(requireContext(), resources.getString(R.string.dlg_message_no_internet),
                Toast.LENGTH_SHORT).show()
            return
        }

        mHomeViewModel = ViewModelProviders.of(this, HomeFactory(requireContext()))[HomeViewModel::class.java]
        mHomeViewModel.init()

        observeProgressBarVisibility()
        mHomeViewModel.getNewsHeadlineListRepository()?.observe(viewLifecycleOwner, Observer { newsHeadlineResponse ->
            if(newsHeadlineResponse != null) {
                mNewsHeadlineResponse = newsHeadlineResponse
                if(null != mNewsHeadlineResponse.articles && mNewsHeadlineResponse.articles!!.isNotEmpty()) {
                    empty_container.visibility = View.GONE
                    setAdapter(mNewsHeadlineResponse.articles!!)
                } else {
                    empty_container.visibility = View.VISIBLE
                }
            } else {
                Toast.makeText(context, resources.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observeProgressBarVisibility() {
        mHomeViewModel.getProgress()?.observe(viewLifecycleOwner, Observer { isProgressVisible ->
            if (isProgressVisible!!) {
                progress_bar_container.visibility = View.VISIBLE
            } else {
                progress_bar_container.visibility = View.GONE
            }
        })
    }

    private fun setAdapter(articles: List<Article>) {
        mRecyclerView.visibility = View.VISIBLE
        mAdapter = HeadlinesListAdapter(requireContext(), articles, false, this)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        mRecyclerView.adapter = mAdapter
    }

    override fun onNewsHeadlinesClick(newsArticle: Article) {
        val intent = Intent(context, NewsDetailsActivity::class.java)
        intent.putExtra(Constants.ARTICLE_DATA, newsArticle.url)
        startActivity(intent)
    }

    override fun onNewsSaveClick(newsArticle: Article) {
        mNewsViewModel.insert(newsArticle)
        Toast.makeText(context, resources.getString(R.string.news_saved), Toast.LENGTH_SHORT).show()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun updateNewsHeadlines(event: String?) {
        when (event) {
            Constants.UPDATE_NEWS_HEADLINES -> {
                mRecyclerView.visibility = View.GONE
                getNewsHeadlinesList()
            }
        }
    }
}