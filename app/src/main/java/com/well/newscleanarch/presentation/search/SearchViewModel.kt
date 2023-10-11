package com.well.newscleanarch.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.well.newscleanarch.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNewsUseCase: NewsUseCases
) : ViewModel() {

    private val sources = listOf("bbc-news", "abc-news", "al-jazeera-english")

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(searchEvent: SearchEvent) {
        when (searchEvent) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = searchEvent.searchQuery)
            }
            is SearchEvent.SearchNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles = searchNewsUseCase.searchNewsUseCase(
            searchQuery = state.value.searchQuery,
            sources = sources,
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)
    }


}