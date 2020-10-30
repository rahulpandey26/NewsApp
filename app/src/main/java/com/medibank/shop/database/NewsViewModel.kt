package com.medibank.shop.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.medibank.shop.home.model.Article

internal class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val mNoteRepository: NewsRepository = NewsRepository(application)
    private val mAllNews: LiveData<List<Article?>?>?

    init {
        mAllNews = mNoteRepository.getAllNews()
    }

    val allNews: LiveData<List<Article?>?>?
        get() = mAllNews

    fun insert(article: Article?) {
        mNoteRepository.insert(article)
    }
}