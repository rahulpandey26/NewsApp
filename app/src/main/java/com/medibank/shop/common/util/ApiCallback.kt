package com.medibank.shop.common.util

import android.content.Context
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback;

abstract class ApiCallback<T>(context: Context?) : Callback<T> {

    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        if (response.isSuccessful) {
            onResponseSuccess(response.body())
        } else {
            onResponseFailure(response.message())
        }
    }

    override fun onFailure(call: Call<T?>, t: Throwable) {
        onResponseFailure(t.message)
    }

    abstract fun onResponseSuccess(response: T?)

    abstract fun onResponseFailure(errorMessage: String?)
}