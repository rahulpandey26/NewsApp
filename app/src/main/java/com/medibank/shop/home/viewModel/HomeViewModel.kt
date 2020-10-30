package com.medibank.shop.home.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.medibank.shop.home.model.NewsHeadlineResponse
import com.medibank.shop.home.model.NewsSourceResponse
import com.medibank.shop.home.repository.HomeRepository

class HomeViewModel(private val mContext: Context) : ViewModel() {

    private var mNewsHeadlineLiveData: MutableLiveData<NewsHeadlineResponse>? = null
    private var mNewsSourceLiveData: MutableLiveData<NewsSourceResponse>? = null
    private var mHomeRepository: HomeRepository? = null
    private var mProgressVisibility: MutableLiveData<Boolean>? = null

    fun init() {
        mHomeRepository = HomeRepository.getInstance(mContext)

        mNewsHeadlineLiveData = mHomeRepository?.getNewsHeadlinesList(mContext)

        if (mNewsSourceLiveData == null) {
            mNewsSourceLiveData = mHomeRepository?.getNewsSourceList(mContext)
        }
    }

    fun getNewsHeadlineListRepository(): LiveData<NewsHeadlineResponse>? {
        return mNewsHeadlineLiveData
    }

    fun getNewsSourceListRepository(): LiveData<NewsSourceResponse>? {
        return mNewsSourceLiveData
    }

    fun getProgress(): LiveData<Boolean?>? {
        mProgressVisibility = mHomeRepository?.isDataLoading()
        return mProgressVisibility
    }
}