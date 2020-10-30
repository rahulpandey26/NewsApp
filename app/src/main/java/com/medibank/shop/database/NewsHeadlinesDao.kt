package com.medibank.shop.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.medibank.shop.home.model.Article
import com.medibank.shop.home.model.NewsHeadlineResponse

@Dao
interface NewsHeadlinesDao {

    @Query("Select * from headlines_table")
    fun getAllSavedNews(): LiveData<List<Article?>?>?

    @Insert
    fun insert(article: Article?)

}