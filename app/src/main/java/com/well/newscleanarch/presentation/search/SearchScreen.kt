package com.well.newscleanarch.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.presentation.Dimens
import com.well.newscleanarch.presentation.common.ArticleList
import com.well.newscleanarch.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(
                top = Dimens.MediumPadding,
                start = Dimens.MediumPadding,
                end = Dimens.MediumPadding
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChanged = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) })

        Spacer(modifier = Modifier.height(Dimens.MediumPadding))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(
                articles = articles,
                onClick = { article ->
                    navigateToDetails(article)
                },
            )
        }

    }
}