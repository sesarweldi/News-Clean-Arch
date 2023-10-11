package com.well.newscleanarch.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems
import com.well.newscleanarch.domain.model.Article
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.well.newscleanarch.presentation.Dimens
import com.well.newscleanarch.presentation.common.ArticleList
import com.well.newscleanarch.presentation.common.SearchBar
import com.well.newscleanarch.ui.theme.PlaceHolderColor
import com.well.newscleanarch.ui.theme.PoppinsSemiBold
import com.well.newscleanarch.util.StringConstant

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = Dimens.MediumPadding,
                start = Dimens.MediumPadding,
                end = Dimens.MediumPadding
            )
            .statusBarsPadding()
    ) {

        Text(
            text = StringConstant.NEWS_CLEAN_ARCH,
            style = MaterialTheme.typography.displaySmall.copy(fontFamily = PoppinsSemiBold),
            fontFamily = PoppinsSemiBold,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(Dimens.MediumPadding))

        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChanged = {},
            onSearch = {},
            onClick = navigateToSearch
        )

        Spacer(modifier = Modifier.height(Dimens.MediumPadding))

        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.ExtraSmallPadding)
                .basicMarquee(),
            fontSize = 12.sp,
            color = PlaceHolderColor
        )

        Spacer(modifier = Modifier.height(Dimens.MediumPadding))

        ArticleList(articles = articles, onClick = {
            navigateToDetails(it)
        })
    }
}