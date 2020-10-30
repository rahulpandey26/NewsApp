package com.medibank.shop.common.util

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