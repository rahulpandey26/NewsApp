package com.medibank.shop.home.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsHeadlineResponse {

    @SerializedName("status")
    @Expose
     val status: String? = null

    @SerializedName("totalResults")
    @Expose
     val totalResults = 0

    @SerializedName("articles")
    @Expose
     val articles: List<Article>? = null
}