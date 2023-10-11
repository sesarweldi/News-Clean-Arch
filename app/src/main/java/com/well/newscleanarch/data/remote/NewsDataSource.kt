package com.well.newscleanarch.data.remote

import com.well.newscleanarch.data.remote.dto.NewsResponse
import com.well.newscleanarch.util.ApiUrlConstant.EVERYTHING
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDataSource {

    @GET(EVERYTHING)
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int? = 10,
        @Query("sources") sources: String,
    ): NewsResponse

    @GET(EVERYTHING)
    suspend fun searchNews(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int? = 10,
        @Query("sources") sources: String,
    ): NewsResponse

}