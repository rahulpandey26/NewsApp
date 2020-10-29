package com.medibank.shop.home.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.medibank.shop.home.ui.fragment.HeadlinesFragment
import com.medibank.shop.home.ui.fragment.SavedFragment
import com.medibank.shop.home.ui.fragment.SourcesFragment

@Suppress("DEPRECATION")
internal class HomeTabAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HeadlinesFragment()
            }
            1 -> {
                SourcesFragment()
            }
            2 -> {
                SavedFragment()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}