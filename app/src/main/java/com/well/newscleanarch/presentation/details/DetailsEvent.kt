package com.well.newscleanarch.presentation.details

import com.well.newscleanarch.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object  RemoveSideEffect: DetailsEvent()
}