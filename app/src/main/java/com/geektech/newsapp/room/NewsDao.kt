package com.geektech.newsapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.geektech.newsapp.models.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAll(): List<News>


    @Insert
    fun insert(news: News)

    @Query("SELECT * FROM news ORDER BY createdAd DESC")
    fun sortAll(): List<News>

    @Delete
    fun deleteItem(news: News)

}

