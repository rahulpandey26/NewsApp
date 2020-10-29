package com.medibank.shop.home.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.medibank.shop.home.viewModel.HomeViewModel


class HomeFactory(private val mContext: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  HomeViewModel(mContext) as T;
    }
}