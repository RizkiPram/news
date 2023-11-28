package com.example.rakamin.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rakamin.Injection
import com.example.rakamin.remote.api.ApiConfig
import com.example.rakamin.remote.response.ArticlesItem
import com.example.rakamin.remote.response.NewsResponse
import com.example.rakamin.repository.NewsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalArgumentException

class MainViewModel(newsRepository: NewsRepository):ViewModel() {
    val getNews:LiveData<PagingData<ArticlesItem>> = newsRepository.getNews().cachedIn(viewModelScope)
//    private val _listAllNews=MutableLiveData<List<ArticlesItem>>()
//    val listAllNews:MutableLiveData<List<ArticlesItem>> = _listAllNews
//
//    fun getAllNews(){
//        val client = ApiConfig.getApiService().getAllNews()
//        client.enqueue(object : Callback<NewsResponse>{
//            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
//                val responseBody = response.body()
//                if (response.isSuccessful){
//                    if (responseBody != null){
//                        _listAllNews.value=responseBody.articles
//                    }
//                }else{
//                    Log.d(TAG,"Response Not Success : ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                Log.d(TAG,"onFailure: ${t.message}")
//            }
//        })
//    }
class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
    companion object{
        const val TAG="MainViewModel"
    }
}