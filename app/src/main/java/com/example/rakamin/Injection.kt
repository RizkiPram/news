package com.example.rakamin

import android.content.Context
import com.example.rakamin.remote.api.ApiConfig
import com.example.rakamin.repository.NewsRepository

object Injection {
    fun provideRepository(context: Context):NewsRepository{
        val apiService = ApiConfig.getApiService()
        return NewsRepository(apiService)
    }
}