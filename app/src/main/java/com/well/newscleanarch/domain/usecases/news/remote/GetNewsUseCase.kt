package com.well.newscleanarch.domain.usecases.news.remote

import androidx.paging.PagingData
import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources)
    }
}