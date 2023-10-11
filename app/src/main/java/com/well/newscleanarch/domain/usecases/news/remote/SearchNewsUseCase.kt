package com.well.newscleanarch.domain.usecases.news.remote

import androidx.paging.PagingData
import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNewsUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}