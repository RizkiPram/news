package com.example.rakamin.remote.api

import com.example.rakamin.remote.response.ArticlesItem
import com.example.rakamin.remote.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything?q=bitcoin&apiKey=25eae1cd641f4d8e81e4e3ec5aee2256")
    suspend fun getAllNews(
        @Query("pageSize") pageSize:Int,
        @Query("page") page:Int
    ): NewsResponse

    companion object {
        const val API_KEY = "25eae1cd641f4d8e81e4e3ec5aee2256"
    }


}