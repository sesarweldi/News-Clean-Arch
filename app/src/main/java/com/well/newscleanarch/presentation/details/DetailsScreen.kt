package com.well.newscleanarch.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.domain.model.Source
import com.well.newscleanarch.presentation.Dimens
import com.well.newscleanarch.presentation.common.DetailsTopBar
import com.well.newscleanarch.ui.theme.NewsCleanArchTheme
import com.well.newscleanarch.ui.theme.PoppinsSemiBold
import com.well.newscleanarch.ui.theme.TextCaption


@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {
                event(DetailsEvent.UpsertDeleteArticle(article))
            },

            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = Dimens.MediumPadding,
                end = Dimens.MediumPadding,
                top = Dimens.MediumPadding,
            )
        ) {
            item {

                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(Dimens.MediumPadding))

                Text(
                    text = article.title,
                    color = Color.Black,
                    fontFamily = PoppinsSemiBold,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                androidx.compose.material.Text(
                    text = article.content ?: "",
                    style = TextCaption,
                    color = Color.Gray,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    NewsCleanArchTheme {
        DetailsScreen(
            article = Article(
                author = "",
                content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                publishedAt = "2 hours ago",
                source = Source("google-news", "BBC"),
                title = "Gempa M 6,3 Guncang Afghanistan, 120 Orang Tewas-Ribuan Terluka - detikNews",
                url = "https://news.google.com/rss/articles/CBMibGh0dHBzOi8vbmV3cy5kZXRpay5jb20vaW50ZXJuYXNpb25hbC9kLTY5NzA3ODMvZ2VtcGEtbS02My1ndW5jYW5nLWFmZ2hhbmlzdGFuLTEyMC1vcmFuZy10ZXdhcy1yaWJ1YW4tdGVybHVrYdIBcGh0dHBzOi8vbmV3cy5kZXRpay5jb20vaW50ZXJuYXNpb25hbC9kLTY5NzA3ODMvZ2VtcGEtbS02My1ndW5jYW5nLWFmZ2hhbmlzdGFuLTEyMC1vcmFuZy10ZXdhcy1yaWJ1YW4tdGVybHVrYS9hbXA?oc=5",
                urlToImage = ""
            ), event = {}, {})
    }

}