package com.example.rakamin.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rakamin.adapter.AllNewsAdapter
import com.example.rakamin.adapter.HeadlineNewsAdapter
import com.example.rakamin.adapter.LoadingStateAdapter
import com.example.rakamin.databinding.ActivityMainBinding
import com.example.rakamin.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModel.ViewModelFactory(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAllNewsData()
        setHeadlineNewsData()
    }

    private fun setAllNewsData() {
        binding.rvAllNews.apply {
            val newsAdapter = AllNewsAdapter()
            adapter = newsAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter{newsAdapter.retry()}
            )
            layoutManager = LinearLayoutManager(this@MainActivity)
            viewModel.getNews.observe(this@MainActivity){
                newsAdapter.submitData(lifecycle,it)
            }
        }
    }
    private fun setHeadlineNewsData(){
        binding.rvHeadlineNews.apply {
            val headlineAdapter = HeadlineNewsAdapter()
            adapter=headlineAdapter
            layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            viewModel.getHeadlineNews.observe(this@MainActivity){
                headlineAdapter.submitData(lifecycle,it)
            }
        }
    }
}