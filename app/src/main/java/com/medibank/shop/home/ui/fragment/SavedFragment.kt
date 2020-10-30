package com.medibank.shop.home.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medibank.shop.R
import com.medibank.shop.common.util.Constants
import com.medibank.shop.database.NewsViewModel
import com.medibank.shop.home.adapter.HeadlinesListAdapter
import com.medibank.shop.home.model.Article
import com.medibank.shop.home.ui.activity.NewsDetailsActivity

class SavedFragment : Fragment() , HeadlinesListAdapter.OnNewsHeadlinesListener{

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: HeadlinesListAdapter
    private lateinit var mNewsViewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        initializeViews(rootView)
        return rootView
    }

    private fun initializeViews(rootView: View?) {
        mRecyclerView = rootView!!.findViewById(R.id.recycler_view)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        mNewsViewModel.allNews?.observe(viewLifecycleOwner,
            Observer<List<Article?>?> { articles ->
                // update recycler view
                setAdapter(articles as List<Article>)
            })
    }

    private fun setAdapter(articles: List<Article>) {
        mAdapter = HeadlinesListAdapter(requireContext(), articles, true,this)
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

    }
}