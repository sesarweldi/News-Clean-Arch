package com.well.newscleanarch.presentation.onboarding.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.well.newscleanarch.R
import com.well.newscleanarch.presentation.onboarding.OnBoarding
import com.well.newscleanarch.presentation.onboarding.OnBoardingEvent
import com.well.newscleanarch.ui.theme.BottomCardShape
import com.well.newscleanarch.ui.theme.Poppins
import com.well.newscleanarch.ui.theme.PoppinsSemiBold
import com.well.newscleanarch.ui.theme.TextCaption
import com.well.newscleanarch.util.StringConstant
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(
    modifier: Modifier = Modifier,
    data: List<OnBoarding>,
    pagerState: PagerState,
    event: (OnBoardingEvent) -> Unit
) {
    Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(data[page].backgroundColor)
                ) {
                    Image(
                        painter = painterResource(id = data[page].image),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Card(
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                elevation = 0.dp,
                shape = BottomCardShape.large,

                ) {
                Box {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        PagerIndicator(items = data, currentPage = pagerState.currentPage)
                        Column {
                            Text(
                                text = data[pagerState.currentPage].title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 20.dp, end = 30.dp),
                                color = data[pagerState.currentPage].backgroundColor,
                                fontFamily = PoppinsSemiBold,
                                textAlign = TextAlign.Right,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold
                            )

                            Text(
                                text = data[pagerState.currentPage].desc,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 20.dp, end = 20.dp, start = 40.dp),
                                style = TextCaption,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(30.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val scope = rememberCoroutineScope()

                            if (pagerState.currentPage == 2) {
                                Button(
                                    onClick = {
                                        scope.launch {
                                            event(OnBoardingEvent.SaveAppEntry)
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = data[pagerState.currentPage].backgroundColor
                                    ),
                                    contentPadding = PaddingValues(vertical = 12.dp),
                                    elevation = ButtonDefaults.elevation(
                                        defaultElevation = 0.dp
                                    ),
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(20)
                                ) {
                                    Text(
                                        text = StringConstant.GET_STARTED, color = Color.White,
                                        fontSize = 14.sp,
                                        fontFamily = Poppins,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            } else {
                                TextButton(onClick = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(page = 2)
                                    }
                                }) {
                                    Text(
                                        text = StringConstant.SKIP_NOW,
                                        color = Color(0xFF292D32),
                                        fontFamily = Poppins,
                                        textAlign = TextAlign.Right,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }

                                OutlinedButton(
                                    onClick = {
                                        scope.launch {
                                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                                        }
                                    },
                                    shape = RoundedCornerShape(50),
                                    border = BorderStroke(
                                        14.dp,
                                        data[pagerState.currentPage].backgroundColor
                                    ),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = data[pagerState.currentPage].backgroundColor),
                                    modifier = Modifier.size(64.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_right_arrow),
                                        contentDescription = "",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }

    }
}

@Composable
fun PagerIndicator(items: List<OnBoarding>, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        repeat(items.size) {
            Indicator(
                isSelected = it == currentPage,
                color = items[it].backgroundColor
            )
        }
    }

}

@Composable
fun Indicator(isSelected: Boolean, color: Color) {
    val width = animateDpAsState(targetValue = if (isSelected) 32.dp else 8.dp)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(8.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) color else Color.Gray.copy(alpha = 0.5f)
            )
    )
}
