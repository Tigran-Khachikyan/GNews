package com.gnews.data.network

import com.gnews.data.network.response.GNewsResponse
import retrofit2.Response
import retrofit2.http.*

interface NewsApi {

    @GET("search/")
    suspend fun searchArticles(
        @Query("q") query: String?,
        @Query("lang") language: String?
    ): Response<GNewsResponse>

    @GET("top-headlines/")
    suspend fun getTopHeadlines(
        @Query("topic") query: String?,
        @Query("lang") language: String?
    ): Response<GNewsResponse>
}
