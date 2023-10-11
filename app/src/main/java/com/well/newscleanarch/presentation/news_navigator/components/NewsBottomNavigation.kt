package com.well.newscleanarch.presentation.news_navigator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph
import com.well.newscleanarch.R
import com.well.newscleanarch.presentation.Dimens
import com.well.newscleanarch.ui.theme.NewsCleanArchTheme
import com.well.newscleanarch.ui.theme.TextCaption

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit,
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(
                                Dimens.SmallIconSize
                            ),
                        )
                        Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding))
                        Text(text = item.text, style = TextCaption)

                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                ),
            )
        }

    }

}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String,
)

@Preview
@Composable
fun NewsBottomNavigationPreview() {
    NewsCleanArchTheme {
        NewsBottomNavigation(items = listOf(
            BottomNavigationItem(icon = R.drawable.ic_public, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        ), selected = 0, onItemClick = {})
    }
}