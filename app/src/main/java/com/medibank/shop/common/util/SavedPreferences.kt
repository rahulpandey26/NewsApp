package com.medibank.shop.common.util

import android.content.Context
import android.content.SharedPreferences

class SavedPreferences(context: Context) {

    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(user_perf, Context.MODE_PRIVATE)
    }

    companion object {

        private const val user_perf = "UserData"
        private var savePreferences: SavedPreferences? = null

        fun getInstance(context: Context): SavedPreferences {
            if (savePreferences == null) {
                savePreferences = SavedPreferences(context)
            }
            return savePreferences as SavedPreferences
        }
    }

    fun setIsImageCrossed(key: String?, value: Boolean): Boolean {
        return sharedPreferences!!.edit().putBoolean(key, value).commit()
    }

    fun getIsImageCrossed(key: String?): Boolean? {
        return if (!sharedPreferences!!.contains(key)) {
            return false
        } else {
            this.sharedPreferences?.getBoolean(key, false)
        }
    }
}