package com.medibank.shop.database

import android.app.Application
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.medibank.shop.home.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun noteDao(): NewsHeadlinesDao?

    private class PopulateDbAsyncTask internal constructor(newsDatabase: NewsDatabase) :
        AsyncTask<Void?, Void?, Void?>() {
        private val newsHeadlinesDao: NewsHeadlinesDao = newsDatabase.noteDao()!!

        override fun doInBackground(vararg params: Void?): Void? {
            // add all in news model class
            return null
        }
    }

    companion object {
        private var mInstance: NewsDatabase? = null

        @Synchronized
        fun getInstance(application: Application): NewsDatabase {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(application, NewsDatabase::class.java,
                        "headlines_table").fallbackToDestructiveMigration()
                        .addCallback(roomCallBack)
                        .build()
            }
            return mInstance as NewsDatabase
        }

        private val roomCallBack: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                mInstance?.let {
                    PopulateDbAsyncTask(it)
                        .execute()
                }
            }
        }
    }
}