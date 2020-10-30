package com.medibank.shop.home.ui.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.medibank.shop.R
import com.medibank.shop.common.util.Constants
import com.medibank.shop.common.util.NetworkUtil
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var mNewsArticleUrl: String
    private lateinit var mSwipeForLoadingUrl: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        mNewsArticleUrl = intent.getStringExtra(Constants.ARTICLE_DATA)
        setWebViewSettings()
        loadUrl()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebViewSettings() {
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.databaseEnabled = true
        webView.settings.minimumFontSize = 1
        webView.settings.minimumLogicalFontSize = 1
        webView.isClickable = true
    }

    private fun loadUrl() {
        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                webView.loadUrl(url)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress_bar_container.visibility = View.VISIBLE
                invalidateOptionsMenu()
            }

            override fun onPageFinished(view: WebView, url: String) {
                progress_bar_container.visibility = View.GONE
            }
        }

        if (NetworkUtil.isNetworkAvailable(this)) {
            progress_bar_container.visibility = View.VISIBLE
            webView.visibility = View.VISIBLE
            tv_webView_noInternetAvail.visibility = View.GONE
            webView.loadUrl(mNewsArticleUrl)
        } else {
            webView.visibility = View.GONE
            tv_webView_noInternetAvail.visibility = View.VISIBLE
        }

    }
}

