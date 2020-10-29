package com.medibank.shop.home.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.medibank.shop.common.util.ApiCallback
import com.medibank.shop.home.model.NewsHeadlineResponse
import com.medibank.shop.home.model.NewsSourceResponse
import com.medibank.shop.home.util.HomeApiManager

class HomeRepository(private val mContext: Context) {

    private val mHomeApiManager: HomeApiManager = HomeApiManager(mContext)
    private val mProgressbarObservable = MutableLiveData<Boolean>()

    companion object {
        private var mHomeRepository: HomeRepository? = null

        fun getInstance(context: Context): HomeRepository? {
            if (mHomeRepository == null) {
                mHomeRepository = HomeRepository(context)
            }
            return mHomeRepository
        }
    }

    fun getNewsHeadlinesList(context: Context): MutableLiveData<NewsHeadlineResponse> {
        mProgressbarObservable.value = true
        val newsHeadlineResponse: MutableLiveData<NewsHeadlineResponse> = MutableLiveData()

        mHomeApiManager.getNewsHeadlinesList(object : ApiCallback<NewsHeadlineResponse>(context) {
            override fun onResponseSuccess(response: NewsHeadlineResponse?) {
                newsHeadlineResponse.value = response
                mProgressbarObservable.value = false
            }

            override fun onResponseFailure(errorMessage: String?) {
                newsHeadlineResponse.value = null
                mProgressbarObservable.value = false
            }

        })

        return newsHeadlineResponse
    }

    fun getNewsSourceList(context: Context): MutableLiveData<NewsSourceResponse> {
        mProgressbarObservable.value = true
        val newsSourceResponse: MutableLiveData<NewsSourceResponse> = MutableLiveData()

        mHomeApiManager.getNewsSourceList(object : ApiCallback<NewsSourceResponse>(context) {
            override fun onResponseSuccess(response: NewsSourceResponse?) {
                newsSourceResponse.value = response
                mProgressbarObservable.value = false
            }

            override fun onResponseFailure(errorMessage: String?) {
                newsSourceResponse.value = null
                mProgressbarObservable.value = false
            }

        })

        return newsSourceResponse
    }

    fun isDataLoading() : MutableLiveData<Boolean>? {
        return mProgressbarObservable
    }
}