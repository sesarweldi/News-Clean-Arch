package com.well.newscleanarch.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.domain.model.Source
import com.well.newscleanarch.presentation.Dimens
import com.well.newscleanarch.ui.theme.NewsCleanArchTheme
import com.well.newscleanarch.ui.theme.PoppinsSemiBold
import com.well.newscleanarch.ui.theme.TextCaption
import com.well.newscleanarch.R
import com.well.newscleanarch.util.TimeAgoUtil

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context).data(article.urlToImage).build()

    Row(modifier = modifier.clickable { onClick() }) {

        AsyncImage(
            model = imageRequest,
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimens.CardPadding)
                .height(Dimens.ArticleCardSize)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.body1.copy(fontFamily = PoppinsSemiBold),
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = article.source.name, style = TextCaption, color = Color.Black)
                Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding))
                Icon(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    modifier = Modifier.size(Dimens.SmallIconSize),
                    contentDescription = null,
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding))
                Text(
                    text = TimeAgoUtil.getTimeAgoDate(article.publishedAt ?: ""),
                    style = TextCaption,
                    color = Color.Black
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    NewsCleanArchTheme {
        ArticleCard(
            article = Article(
                author = "",
                content = "ko amdasm aksd kal maskl dma alksdm as asl dkmasl asldkmaskl maslk maslk malkmaslkd am",
                description = "",
                publishedAt = "2 hours ago",
                source = Source("", "BBC"),
                title = "Ukraina Kena Karma! Sempat Serang Rusia dengan Senjata Telarang Kini 31 Drone Ditembak Jatuh Moskow - Tribunnews",
                url = "",
                urlToImage = ""
            ),
        ) {

        }
    }
}
