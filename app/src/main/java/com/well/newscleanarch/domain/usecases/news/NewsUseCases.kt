package com.well.newscleanarch.domain.usecases.news

import com.well.newscleanarch.domain.usecases.news.remote.GetNewsUseCase
import com.well.newscleanarch.domain.usecases.news.remote.SearchNewsUseCase
import com.well.newscleanarch.domain.usecases.news.room.DeleteArticleUseCase
import com.well.newscleanarch.domain.usecases.news.room.SelectArticleUseCase
import com.well.newscleanarch.domain.usecases.news.room.SelectArticlesUseCase
import com.well.newscleanarch.domain.usecases.news.room.UpsertArticleUseCase

data class NewsUseCases(
    val getNewsUseCase: GetNewsUseCase,
    val searchNewsUseCase: SearchNewsUseCase,
    val upsertArticleUseCase: UpsertArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val selectArticlesUseCase: SelectArticlesUseCase,
    val selectArticleUseCase: SelectArticleUseCase,
)
