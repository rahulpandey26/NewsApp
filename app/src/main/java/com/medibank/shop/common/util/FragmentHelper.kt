package com.medibank.shop.common.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class FragmentHelper {

    companion object {
        /**
         * Replaces fragment without adding it to the back stack .
         */
        fun replaceFragment(activity: FragmentActivity, fragment: Fragment, containerId: Int,
            tag: String?) {
            if (!activity.isFinishing) {
                val manager: FragmentManager = activity.supportFragmentManager
                manager.beginTransaction().replace(containerId, fragment, tag)
                    .commitAllowingStateLoss()
            }
        }
    }
}