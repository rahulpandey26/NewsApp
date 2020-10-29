package com.medibank.shop.common.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.util.ArrayList

class AppUtility {

    companion object {

        var mSelectedSourceList : ArrayList<String> = ArrayList()

        fun setSelectedNewsSource(selectedSourceList : ArrayList<String>) {
            mSelectedSourceList =  selectedSourceList
        }

        fun getSelectedNewsSource(): ArrayList<String> {
          return mSelectedSourceList
        }
    }
}