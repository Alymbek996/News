package com.geektech.newsapp.room

import androidx.room.*
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

    @Update
    fun update (news: News)

}

