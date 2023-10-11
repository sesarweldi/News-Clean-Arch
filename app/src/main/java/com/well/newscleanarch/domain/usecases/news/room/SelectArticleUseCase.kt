package com.well.newscleanarch.domain.usecases.news.room

import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.domain.repository.NewsRepository

class SelectArticleUseCase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }
}