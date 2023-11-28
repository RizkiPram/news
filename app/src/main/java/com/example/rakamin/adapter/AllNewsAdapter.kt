package com.example.rakamin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rakamin.databinding.ItemAllNewsBinding
import com.example.rakamin.remote.response.ArticlesItem

class AllNewsAdapter() : PagingDataAdapter<ArticlesItem,AllNewsAdapter.ViewHolder>(DIFF_CALLBACK) {
    inner class ViewHolder(private val binding:ItemAllNewsBinding) : RecyclerView.ViewHolder(binding.root){
        fun itemBind(data:ArticlesItem){
            binding.apply {
                Glide.with(itemView)
                    .load(data.urlToImage)
                    .into(imgNewsAll)
                tvTittleNews.text=data.title
                tvNewsWriter.text=data.author
                tvDateNewsAll.text=data.publishedAt
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val data = getItem(position)
        if (data != null) {
            holder.itemBind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAllNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(
                oldItem: ArticlesItem,
                newItem: ArticlesItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ArticlesItem,
                newItem: ArticlesItem
            ): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }
}