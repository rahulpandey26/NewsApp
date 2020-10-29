package com.medibank.shop.home.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsSourceResponse {

    @SerializedName("status")
    @Expose
    val status: String? = null

    @SerializedName("totalResults")
    @Expose
    val totalResults = 0

    @SerializedName("sources")
    @Expose
    val sources: List<Source>? = null
}