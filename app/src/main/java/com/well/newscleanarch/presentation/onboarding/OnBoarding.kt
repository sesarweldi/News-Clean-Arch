package com.well.newscleanarch.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.well.newscleanarch.R
import com.well.newscleanarch.ui.theme.GreenColor
import com.well.newscleanarch.ui.theme.PrimaryColor
import com.well.newscleanarch.ui.theme.RedColor

data class OnBoarding(
    val title: String,
    val desc: String,
    @DrawableRes val image: Int,
    val backgroundColor: Color,
)


val onBoardingData = listOf(
    OnBoarding(
        title = "Lorem Ipsum",
        desc = "Lorem Ipsum Sit Amet dolo asu tenan jingan bangsat",
        image = R.drawable.airterjun_lekeleke,
        backgroundColor = PrimaryColor,
    ),
    OnBoarding(
        title = "Lorem Ipsum",
        desc = "Lorem Ipsum Sit Amet dolo asu tenan jingan bangsat",
        image = R.drawable.desa_pinggiran,
        backgroundColor = GreenColor,
    ),
    OnBoarding(
        title = "Lorem Ipsum",
        desc = "Lorem Ipsum Sit Amet dolo asu tenan jingan bangsat",
        image = R.drawable.desa_pinggiran2,
        backgroundColor = RedColor,
    )
)