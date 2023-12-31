package com.example.rakamin.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rakamin.remote.api.ApiService
import com.example.rakamin.remote.response.ArticlesItem
import okio.IOException
import retrofit2.HttpException

class HeadlineNewsPagingSource (private val apiService: ApiService) : PagingSource<Int,ArticlesItem>() {
    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let { ancorPosition ->
            state.closestPageToPosition(ancorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(ancorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        val page = params.key ?: NewsPagingSource.INITIAL_PAGE_INDEX
        return try {
            val response = apiService.getHeadlineNews(pageSize = 5,page)
            LoadResult.Page(
                response.articles,
                prevKey = if ( page == NewsPagingSource.INITIAL_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.articles.isEmpty()) null else page.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }catch (exception: HttpException){
            return LoadResult.Error(exception)
        }
    }

}