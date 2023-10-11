package com.well.newscleanarch.domain.usecases.news.room

import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.domain.repository.NewsRepository

class UpsertArticleUseCase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article)
    }
}