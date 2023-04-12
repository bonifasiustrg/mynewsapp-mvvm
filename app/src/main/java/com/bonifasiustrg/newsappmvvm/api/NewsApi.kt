package com.bonifasiustrg.newsappmvvm.api


import com.bonifasiustrg.newsappmvvm.models.NewsResponse
import com.bonifasiustrg.newsappmvvm.utils.Constants
import com.bonifasiustrg.newsappmvvm.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    suspend fun getBreakingNews(
        @Query("country") country : String = "us",
        @Query("page") pageNumber: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything?apiKey=$API_KEY")
    suspend fun searchForNews(
        @Query("q") searchQuery : String,
        @Query("page") pageNumber: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<NewsResponse>
}