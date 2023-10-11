package com.well.newscleanarch.presentation.search

import androidx.paging.PagingData
import com.well.newscleanarch.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null,
)