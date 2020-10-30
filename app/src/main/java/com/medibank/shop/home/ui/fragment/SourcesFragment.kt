package com.medibank.shop.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medibank.shop.R
import com.medibank.shop.common.util.AppUtility
import com.medibank.shop.common.util.Constants
import com.medibank.shop.common.util.NetworkUtil
import com.medibank.shop.home.adapter.NewsSourceListAdapter
import com.medibank.shop.home.model.Article
import com.medibank.shop.home.model.NewsSourceResponse
import com.medibank.shop.home.model.Source
import com.medibank.shop.home.ui.activity.HomeActivity
import com.medibank.shop.home.util.HomeFactory
import com.medibank.shop.home.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus

class SourcesFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mApplyBtn: TextView
    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var mNewsSourceResponse: NewsSourceResponse
    private lateinit var mAdapter: NewsSourceListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        initializeViews(rootView)
        return rootView
    }

    private fun initializeViews(rootView: View?) {
        mRecyclerView = rootView!!.findViewById(R.id.recycler_view)
        mApplyBtn = rootView.findViewById(R.id.apply_btn)
        mApplyBtn.visibility = View.VISIBLE
        mApplyBtn.setOnClickListener {
            if (AppUtility.getSelectedNewsSource().size > 0) {
                HomeActivity.mViewPager.currentItem = 0
                EventBus.getDefault().post(Constants.UPDATE_NEWS_HEADLINES)
            } else {
            Toast.makeText(requireContext(), "Please select source", LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getNewsSourceList()
    }

    private fun getNewsSourceList() {
        if (!NetworkUtil.isNetworkAvailable(context)) {
            Toast.makeText(
                context,
                resources.getString(R.string.dlg_message_no_internet),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        mHomeViewModel = ViewModelProviders.of(
            this, HomeFactory(
                requireContext()
            )
        )[HomeViewModel::class.java]
        mHomeViewModel.init()

        observeProgressBarVisibility()
        mHomeViewModel.getNewsSourceListRepository()
            ?.observe(viewLifecycleOwner, Observer { newsSourceListResponse ->
                if (newsSourceListResponse != null) {
                    mNewsSourceResponse = newsSourceListResponse
                    if (null != mNewsSourceResponse.sources) {
                        setAdapter(mNewsSourceResponse.sources!!)
                    }
                } else {
                    Toast.makeText(
                        context,
                        resources.getString(R.string.something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
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

    private fun setAdapter(articles: List<Source>) {
        mAdapter = NewsSourceListAdapter(requireContext(), articles)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        mRecyclerView.adapter = mAdapter
    }
}