package com.well.newscleanarch.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.well.newscleanarch.domain.usecases.news.NewsUseCases
import com.well.newscleanarch.presentation.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    private val sources = listOf("bbc-news", "abc-news", "al-jazeera-english")

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    val allNews = newsUseCases.getNewsUseCase(
        sources = sources,
    ).cachedIn(viewModelScope)
}