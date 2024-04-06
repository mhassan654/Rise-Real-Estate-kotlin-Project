package com.saavatech.riserealestate.presentation.onBoarding

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.common.OnBoardingEvent
import com.saavatech.riserealestate.components.ButtonTextComponent
import com.saavatech.riserealestate.components.RoundedIconButton
import com.saavatech.riserealestate.ui.theme.ButtonBgOne
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun OnBoardingScreen(
//    navController: DestinationsNavigator,
    event: (OnBoardingEvent) -> Unit,
) {
    val pages =
        listOf(
            OnBoardingPage.First,
            OnBoardingPage.Second,
            OnBoardingPage.Third,
        )

    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState(pageCount = { 3 })

    Column(Modifier.fillMaxSize()) {
        TopSection(
            onSkipClick = {
                if (pageState.currentPage + 1 < pages.size) {
                    scope.launch {
                        pageState.scrollToPage(pages.size - 1)
                    }
                }
            },
        )

        Box {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally ){
                HorizontalPager(
                    state = pageState,
                    modifier =
                    Modifier
                        .fillMaxSize()
                    .weight(0.8f),
                ) { page ->
                    OnBoardingPagerScreen(
                        pages[page],
                    )
                }

                Indicators(pages.size, pageState.currentPage)
                FinishButton(
                    modifier = Modifier.height(50.dp),
                    pagerState = pageState,
                ) {
                    scope.launch {
                        if (pageState.currentPage == 2) {
                            event(OnBoardingEvent.SaveAppEntry)
                        } else {
                            pageState.animateScrollToPage(page = pageState.currentPage + 1)
                        }
                    }
                }
            }

        }



        // Define your click handlers outside the composable function
        var onPreviousClicked: () -> Unit = {
            // Define the behavior for the previous button click here
            // For example, you can navigate to the previous page
            // or perform any other action you desire

            if (pageState.currentPage - 1 < pages.size) {
                scope.launch {
                    pageState.scrollToPage(pageState.currentPage - 1)
                }
            }
        }

        var onNextClicked: () -> Unit = {
            // Define the behavior for the next button click here
            // For example, you can navigate to the next page
            // or perform any other action you desire

            if (pageState.currentPage + 1 < pages.size) {
                scope.launch {
                    pageState.scrollToPage(pageState.currentPage + 1)
                }
            }
        }





//        ButtomSection(
//            size = pages.size,
//            index = pageState.currentPage,
//            onPreviousClicked.also { onPreviousClicked = it },
//            onNextClicked.also { onNextClicked = it },
//        )
    }
}

@Composable
fun TopSection(onSkipClick: () -> Unit = {}) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
        )

        // skip button
        TextButton(
            modifier =
                Modifier
                    .align(Alignment.CenterEnd)
                    .background(ButtonBgOne, shape = RoundedCornerShape(20.dp)),
            onClick = onSkipClick,
        ) {
            Text(
                text = "Skip",
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
fun OnBoardingPagerScreen(onBoardingPage: OnBoardingPage) {
    Box {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
            //            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier.padding(vertical = 10.dp),
            ) {
                Text(
                    text = onBoardingPage.title1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center,
                    color = TextColorOne,
                )

                Text(
                    text =
                        buildAnnotatedString {
                            withStyle(
                                style =
                                    SpanStyle(
                                        color = TextColorOne,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight(400),
                                    ),
                            ) {
                                append(onBoardingPage.title2)
                            }
                            append(" ")
                            withStyle(
                                style =
                                    SpanStyle(
                                        color = TextColorBold,
                                        fontWeight = FontWeight(700),
                                        fontSize = 20.sp,
                                    ),
                            ) {
                                append(onBoardingPage.title3)
                            }
                        },
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start,
                    color = TextColorBold,
                )

                Text(
                    modifier =
                        Modifier
                            .padding(top = 20.dp),
                    text = onBoardingPage.description,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center,
                    color = TextColorOne,
                )
            }

            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                    painter = painterResource(id = onBoardingPage.image),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun ButtomSection(
    size: Int,
    index: Int,
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
//        //indicators
            Indicators(size = size, index = index)

            Spacer(modifier = Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                // previous slide click button
                RoundedIconButton(
                    icon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    onClick = {
                        onPreviousClicked.invoke()
                    },
                )

                Spacer(modifier = Modifier.width(10.dp))

                // next slide click button
                ButtonTextComponent(
                    value = "Next",
                    clickAction = {
                        onNextClicked.invoke()
                    },
                    width = 150.dp,
                )
            }
        }
    }
}

@Composable
fun Indicators(
    size: Int,
    index: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width =
        animateDpAsState(
            targetValue = if (isSelected) 25.dp else 10.dp,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
            label = "",
        )
    Box(
        modifier =
            Modifier
                .height(10.dp)
                .width(width.value)
                .clip(CircleShape)
                .background(
                    if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    },
                ),
    ) {
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier.padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2,
        ) {
            Button(
                onClick = { onClick.invoke() },
                colors =
                    ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                    ),
            ) {
                Text(text = "Finish")
            }
        }
    }
}

// preview on boarding screens

@Preview(showBackground = true)
@Composable
fun FirstScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        OnBoardingPagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        OnBoardingPagerScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Preview(showBackground = true)
@Composable
fun ThirdScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        OnBoardingPagerScreen(onBoardingPage = OnBoardingPage.Third)
    }
}
