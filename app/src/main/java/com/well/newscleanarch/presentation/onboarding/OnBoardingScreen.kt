package com.well.newscleanarch.presentation.onboarding


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.well.newscleanarch.presentation.onboarding.components.OnBoardingPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit
) {

    val items = onBoardingData

    Surface(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialOffscreenLimit = 2,
            infiniteLoop = false,
            pageCount = items.size
        )

        OnBoardingPager(
            modifier = Modifier.fillMaxWidth(),
            data = items,
            pagerState = pagerState,
            event = {
                event(OnBoardingEvent.SaveAppEntry)
            }
        )
    }
}