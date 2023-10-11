package com.well.newscleanarch.presentation.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.well.newscleanarch.presentation.Dimens
import com.well.newscleanarch.presentation.Dimens.MediumPadding
import com.well.newscleanarch.ui.theme.NewsCleanArchTheme
import com.well.newscleanarch.ui.theme.ShimmerColor

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    background(color = ShimmerColor.copy(alpha))
}

@Composable
fun  ArticleCardShimmerEffect(
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimens.CardPadding)
                .height(Dimens.ArticleCardSize)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(horizontal = MediumPadding)
                    .shimmerEffect()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(15.dp)
                        .padding(horizontal = MediumPadding)
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(15.dp)
                        .padding(horizontal = MediumPadding)
                        .shimmerEffect()
                )
            }
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun ArticleShimmerEffectPreview() {
    NewsCleanArchTheme {
        ArticleCardShimmerEffect()
    }
    
}