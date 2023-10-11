package com.well.newscleanarch.domain.usecases.news.room

import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticlesUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}