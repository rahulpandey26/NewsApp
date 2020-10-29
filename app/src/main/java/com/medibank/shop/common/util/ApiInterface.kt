package com.medibank.shop.common.util

import com.medibank.shop.home.model.NewsHeadlineResponse
import com.medibank.shop.home.model.NewsSourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET(Constants.HEADLINES)
    fun getNewsHeadlineList(@QueryMap(encoded = true) queries: MutableMap<String, String>): Call<NewsHeadlineResponse>

    @GET(Constants.SOURCE)
    fun getNewsSourceList(@QueryMap(encoded = true) queries: MutableMap<String, String>): Call<NewsSourceResponse>
}