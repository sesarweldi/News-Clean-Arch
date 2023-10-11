package com.well.newscleanarch.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.util.ApiUrlConstant

class NewsPagingSource(
    private val newsApi: NewsDataSource,
    private val sources: String,
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(
                page = page,
                apiKey = ApiUrlConstant.API_KEY,
                sources = sources,
            )
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}