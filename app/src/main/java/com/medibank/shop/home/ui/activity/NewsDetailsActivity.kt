package com.medibank.shop.home.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.medibank.shop.R
import com.medibank.shop.common.util.Constants
import com.medibank.shop.home.model.Article
import kotlinx.android.synthetic.main.activity_news_details.*


class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var mNewsArticle: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        mNewsArticle = intent.getParcelableExtra(Constants.ARTICLE_DATA)
        setUIData()
    }

    private fun setUIData() {
        tvNewsTitle.text = mNewsArticle.title
        tvNewsDesc.text = mNewsArticle.description
        Glide.with(this)
            .load(mNewsArticle.urlToImage)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .dontAnimate()
            .dontTransform()
            .into(ivNewsImage)
    }
}