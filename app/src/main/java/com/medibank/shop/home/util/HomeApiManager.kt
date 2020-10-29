package com.medibank.shop.home.util

import android.content.Context
import com.medibank.shop.common.util.*
import com.medibank.shop.home.model.NewsHeadlineResponse
import com.medibank.shop.home.model.NewsSourceResponse
import retrofit2.Call
import java.util.ArrayList

class HomeApiManager(private val mContext: Context) {

    private val mApiInterface: ApiInterface = ApiServiceUtil.getInstance(mContext).getApiInterface()

    fun getNewsHeadlinesList(callback: ApiCallback<NewsHeadlineResponse>) {
        val queries: MutableMap<String, String> = HashMap()
        queries[Constants.HttpReqParamKey.COUNTRY] = "us"

        val selectedSourceList : ArrayList<String> =  AppUtility.getSelectedNewsSource()
        if(selectedSourceList.size > 0) {
            var totalSelectedSource = ""
            for(pos in selectedSourceList.indices) {
                if(selectedSourceList.size == pos -1) {
                    totalSelectedSource = totalSelectedSource + selectedSourceList[pos] + ","
                } else {
                    totalSelectedSource += selectedSourceList[pos]
                }
            }
            queries[Constants.HttpReqParamKey.SOURCE] = totalSelectedSource
        }

        queries[Constants.HttpReqParamKey.API_KEY] = Constants.HttpReqParamKey.API_KEY_VALUE
        val userPostListResponseCall: Call<NewsHeadlineResponse> =
            mApiInterface.getNewsHeadlineList(queries)
        userPostListResponseCall.enqueue(callback)
    }

    fun getNewsSourceList(callback: ApiCallback<NewsSourceResponse>) {
        val queries: MutableMap<String, String> = HashMap()
        queries[Constants.HttpReqParamKey.API_KEY] = Constants.HttpReqParamKey.API_KEY_VALUE
        val userPostListResponseCall: Call<NewsSourceResponse> =
            mApiInterface.getNewsSourceList(queries)
        userPostListResponseCall.enqueue(callback)
    }
}