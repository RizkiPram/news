package com.example.rakamin.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.liveData
import com.example.rakamin.NewsPagingSource
import com.example.rakamin.remote.api.ApiService
import com.example.rakamin.remote.response.ArticlesItem

class NewsRepository(val apiService: ApiService) {
    fun getNews() : LiveData<PagingData<ArticlesItem>>{
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {NewsPagingSource(apiService)}
        ).liveData
    }
}