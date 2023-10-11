package com.well.newscleanarch.domain.usecases.news.room

import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.domain.repository.NewsRepository

class DeleteArticleUseCase(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}