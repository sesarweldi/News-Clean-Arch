package com.well.newscleanarch.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.presentation.Dimens

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit,
) {

    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(
                Dimens.MediumPadding
            ), contentPadding = PaddingValues(all = Dimens.CardPadding)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(article = article, onClick = { onClick(article) })
                }
            }
        }
    }

}

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit,
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(
            Dimens.MediumPadding
        ), contentPadding = PaddingValues(all = Dimens.CardPadding)
    ) {
        items(count = articles.size, key = { articles[it].url }) {
            val article = articles[it]
            ArticleCard(article = article, onClick = { onClick(article) })
        }
    }
}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean {

    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null -> {
            EmptyScreen()
            false
        }
        else -> true
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding)) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = Dimens.MediumPadding)
            )
        }

    }
}