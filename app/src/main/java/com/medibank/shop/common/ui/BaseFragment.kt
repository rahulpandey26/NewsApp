package com.medibank.shop.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.medibank.shop.R
import kotlinx.android.synthetic.main.fragment_base.*

open class BaseFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    fun showProgressBar() {
        progress_bar_container.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progress_bar_container.visibility = View.GONE
    }
}