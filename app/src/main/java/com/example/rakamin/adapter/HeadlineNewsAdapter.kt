package com.example.rakamin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rakamin.databinding.ItemHeadlineBinding
import com.example.rakamin.remote.response.ArticlesItem

class HeadlineNewsAdapter : PagingDataAdapter<ArticlesItem, HeadlineNewsAdapter.ViewHolder>(AllNewsAdapter.DIFF_CALLBACK){
    inner class ViewHolder(private val binding: ItemHeadlineBinding) : RecyclerView.ViewHolder(binding.root){
        fun itemBind(data:ArticlesItem){
            binding.apply {
                Glide.with(itemView)
                    .load(data.urlToImage)
                    .into(imgNewsHeadline)
                tvDateNews.text=data.publishedAt
                tvTittleNews.text=data.title
                tvSourceNews.text=data.source.name
            }
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null){
            holder.itemBind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ItemHeadlineBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
}