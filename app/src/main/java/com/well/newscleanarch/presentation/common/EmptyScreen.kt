package com.well.newscleanarch.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import com.well.newscleanarch.R
import com.well.newscleanarch.ui.theme.PoppinsSemiBold
import com.well.newscleanarch.ui.theme.TextCaption
import com.well.newscleanarch.util.StringConstant
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null
) {

    var message by remember {
        mutableStateOf(parseErrorMessage(error = error))
    }

    var icon by remember {
        mutableStateOf(R.drawable.ic_network_error)
    }

    if (error == null) {
        message = StringConstant.YOU_HAVE_NOT_SAVED_NEWS_SO_FAR
        icon = R.drawable.ic_search_document
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.3f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(alphaAnim = alphaAnimation, message = message, iconId = icon)

}

@Composable
fun EmptyContent(alphaAnim: Float, message: String, iconId: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = Color.DarkGray,
            modifier = Modifier
                .size(120.dp)
                .alpha(alphaAnim)
        )
        Text(
            text = message,
            modifier = Modifier
                .padding(10.dp)
                .alpha(alphaAnim),
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = PoppinsSemiBold,
            fontWeight = FontWeight.SemiBold
        )
    }

}

fun parseErrorMessage(error: LoadState.Error?): String {
    return when(error?.error) {
        is SocketTimeoutException -> {
            StringConstant.SERVER_UNAVAILABLE
        }
        is ConnectException -> {
            StringConstant.INTERNET_UNAVAILABLE
        }
        else -> {
            StringConstant.UNKNOWN_ERROR
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    EmptyContent(alphaAnim = 0.3f, message = "Internet Unavailable", iconId = R.drawable.ic_network_error)
}
