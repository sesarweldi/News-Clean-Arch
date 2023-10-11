package com.well.newscleanarch.data.remote.dto

import com.well.newscleanarch.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)