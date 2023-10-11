package com.well.newscleanarch.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.well.newscleanarch.R
import com.well.newscleanarch.ui.theme.NewsCleanArchTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit,
) {

    TopAppBar(
        title = {}, modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = Color.DarkGray,
            navigationIconContentColor = Color.DarkGray
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = onBookmarkClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = null,
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                )
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_public),
                    contentDescription = null,
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailsTopBarPreview() {
    NewsCleanArchTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background) )
        DetailsTopBar(
            onBrowsingClick = { /*TODO*/ },
            onShareClick = { /*TODO*/ },
            onBookmarkClick = { /*TODO*/ }) {

        }
    }
}