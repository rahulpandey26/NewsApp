package com.medibank.shop.common.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkUtil {

    companion object {
        fun isNetworkAvailable(context: Context?): Boolean {
            if (context == null) return false
            val networkInfo = getNetworkInfo(context)
            return networkInfo != null && networkInfo.isConnected
        }

        private fun getNetworkInfo(context: Context): NetworkInfo? {
            val cm = context.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            return cm.activeNetworkInfo
        }
    }
}