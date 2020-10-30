package com.medibank.shop.database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.medibank.shop.home.model.Article

class NewsRepository(application: Application) {

    private var mNewsHeadlinesDao: NewsHeadlinesDao? = null
    private var mAllNews: LiveData<List<Article?>?>? = null

     init {
        val noteDataBase: NewsDatabase = NewsDatabase.getInstance(application)
        mNewsHeadlinesDao = noteDataBase.noteDao()
        mAllNews = mNewsHeadlinesDao?.getAllSavedNews()
    }

    fun insert(article: Article?) {
        InsertNoteAsyncTask(mNewsHeadlinesDao, DataBaseOperationType.INSERT).execute(article)
    }

    fun getAllNews(): LiveData<List<Article?>?>? {
        return mAllNews
    }

    private class InsertNoteAsyncTask internal constructor(
        newsHeadlinesDao: NewsHeadlinesDao?,
        dataBaseOperationType: DataBaseOperationType
    ) : AsyncTask<Article?, Void?, Void?>() {

        private val mNewsHeadlinesDao: NewsHeadlinesDao? = newsHeadlinesDao
        private val mDataBaseOperationType: DataBaseOperationType = dataBaseOperationType

        override fun doInBackground(vararg params: Article?): Void? {
            when (mDataBaseOperationType) {
                DataBaseOperationType.INSERT -> mNewsHeadlinesDao?.insert(params[0])
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
        }
    }
}