package com.example.rakamin.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rakamin.pagingsource.HeadlineNewsPagingSource
import com.example.rakamin.pagingsource.NewsPagingSource
import com.example.rakamin.remote.api.ApiService
import com.example.rakamin.remote.response.ArticlesItem

class NewsRepository(val apiService: ApiService) {
    fun getNews(): LiveData<PagingData<ArticlesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = { NewsPagingSource(apiService) }
        ).liveData
    }

    fun getHeadlineNews(): LiveData<PagingData<ArticlesItem>>{
        return Pager(
            config = PagingConfig(
                5
            ),
            pagingSourceFactory = {HeadlineNewsPagingSource(apiService)}
        ).liveData
    }
}