package com.medibank.shop.home.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Source {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    var isChecked: Boolean = false
}