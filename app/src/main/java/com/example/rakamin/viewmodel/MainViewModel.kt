package com.example.rakamin.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rakamin.Injection
import com.example.rakamin.remote.response.ArticlesItem
import com.example.rakamin.repository.NewsRepository

class MainViewModel(newsRepository: NewsRepository) : ViewModel() {
    val getNews: LiveData<PagingData<ArticlesItem>> =
        newsRepository.getNews().cachedIn(viewModelScope)
    val getHeadlineNews: LiveData<PagingData<ArticlesItem>> =
        newsRepository.getHeadlineNews().cachedIn(viewModelScope)

    class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(Injection.provideRepository(context)) as T
            }
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}