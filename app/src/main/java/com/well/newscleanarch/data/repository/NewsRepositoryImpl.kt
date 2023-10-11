package com.well.newscleanarch.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.well.newscleanarch.data.local.NewsDao
import com.well.newscleanarch.data.remote.NewsDataSource
import com.well.newscleanarch.data.remote.NewsPagingSource
import com.well.newscleanarch.data.remote.SearchNewsPagingSource
import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsDataSource: NewsDataSource,
    private val newsDao: NewsDao,
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsDataSource,
                    sources = sources.joinToString(separator = ","),
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    dataSource = newsDataSource,
                    sources = sources.joinToString(separator = ","),
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }
}