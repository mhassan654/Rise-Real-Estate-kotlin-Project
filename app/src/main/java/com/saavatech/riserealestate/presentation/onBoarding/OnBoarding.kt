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
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
//import com.saavatech.onboardingscreen.Destinations
//import com.saavatech.onboardingscreen.viewmodel.WelcomeViewModel
import com.saavatech.riserealestate.Destinations
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavHostController,
//    welcomeViewModel: WelcomeViewModel = hiltViewModel()
){

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
    )

    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize()) {
        TopSection()

        val state = rememberPagerState(pageCount = { 3 })
            HorizontalPager(
                state = state,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.8f)
            ) {page->
                OnBoardingPagerScreen(pages[page] )

            }

//        FinishButton(
//            modifier = Modifier.weight(1f),
//            pagerState = state
//        ) {
//            welcomeViewModel.saveOnBoardingState(completed = true)
//            navController.popBackStack()
//            navController.navigate(Destinations.Home.route)
//        }

        ButtomSection(size =pages.size, index=state.currentPage) {
            if (state.currentPage+1 <pages.size){
                scope.launch {
                    state.scrollToPage(state.currentPage+1)
                }

            }
        }

    }

}

@Composable
fun TopSection(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)) {

        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = {}
        ){
            Icon(Icons.AutoMirrored.Outlined.KeyboardArrowLeft, contentDescription = null)
        }

        //skip button
        TextButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Skip", color = MaterialTheme.colorScheme.primary)

        }

    }

}

@Composable
fun OnBoardingPagerScreen(onBoardingPage: OnBoardingPage){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
//            fontSize = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = onBoardingPage.description,
//            fontSize = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

    }

}


@Composable
fun ButtomSection(
    size: Int, index: Int, onNextClicked: ()->Unit
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)) {

        //indicators
        Indicators(size = size, index=index)

        //next button
        FloatingActionButton(
            onClick = onNextClicked,
            modifier = Modifier
                .align(Alignment.CenterEnd),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription =null )
        }

    }

}



@Composable
fun BoxScope.Indicators(size:Int, index: Int){
    
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier=Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size){
            Indicator(isSelected = it==index)
        }
        
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
//    val isSelected =true
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = ""
    )
    Box(
        modifier= Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
    ) {

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 3
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
                Text(text = "Finish")
            }
        }
    }
}