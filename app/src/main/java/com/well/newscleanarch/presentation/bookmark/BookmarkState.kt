package com.well.newscleanarch.presentation.bookmark

import com.well.newscleanarch.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList(),
)