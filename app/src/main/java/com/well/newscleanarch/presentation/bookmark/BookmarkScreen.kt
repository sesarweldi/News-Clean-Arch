package com.well.newscleanarch.presentation.bookmark

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.presentation.Dimens
import com.well.newscleanarch.presentation.common.ArticleList
import com.well.newscleanarch.presentation.navgraph.Route
import com.well.newscleanarch.ui.theme.PoppinsSemiBold
import com.well.newscleanarch.util.StringConstant

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = Dimens.MediumPadding,
                start = Dimens.MediumPadding,
                end = Dimens.MediumPadding
            )
    ) {

        Text(
            text = StringConstant.BOOKMARK,
            style = MaterialTheme.typography.displaySmall.copy(fontFamily = PoppinsSemiBold),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(Dimens.MediumPadding))

        ArticleList(articles = state.articles, onClick = { navigateToDetails(it) })
    }
}