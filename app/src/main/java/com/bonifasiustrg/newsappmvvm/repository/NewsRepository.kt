package com.bonifasiustrg.newsappmvvm.repository

import com.bonifasiustrg.newsappmvvm.api.RetrofitInstance
import com.bonifasiustrg.newsappmvvm.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)


}