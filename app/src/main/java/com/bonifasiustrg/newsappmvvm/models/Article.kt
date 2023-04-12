package com.bonifasiustrg.newsappmvvm.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//THis anotate to show thath this data class is a table for database
@Entity(
    tableName = "articles"
)

data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)