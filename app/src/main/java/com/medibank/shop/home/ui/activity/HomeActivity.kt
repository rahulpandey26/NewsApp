package com.medibank.shop.home.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.medibank.shop.R
import com.medibank.shop.home.adapter.HomeTabAdapter
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    // https://newsapi.org/v2/top-headlines?country=us&apiKey=ecf41ddc61484f218c33c11bff044387
    // api key ecf41ddc61484f218c33c11bff044387
    companion object {
        lateinit var mViewPager: ViewPager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewPager = findViewById(R.id.viewPager)
        setTabLayout()
    }

    private fun setTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.headlines)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.sources)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.saved)))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = HomeTabAdapter(this, supportFragmentManager, tabLayout.tabCount)
        mViewPager.adapter = adapter
        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}
