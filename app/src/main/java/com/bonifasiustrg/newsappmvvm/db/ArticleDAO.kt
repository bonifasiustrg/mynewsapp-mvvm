package com.bonifasiustrg.newsappmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bonifasiustrg.newsappmvvm.models.Article

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //this will replace article if already exists in our database
    suspend fun upsert(article: Article): Long  //upsert-> Update or Insert

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}