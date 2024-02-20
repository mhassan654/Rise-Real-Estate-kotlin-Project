package com.saavatech.riserealestate.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.saavatech.riserealestate.Destinations
import com.saavatech.riserealestate.DestinationsNavigator
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.common.ButtonTextComponent
import com.saavatech.riserealestate.ui.theme.ButtonBgOne
import com.saavatech.riserealestate.ui.theme.WelcomeBgOne
import com.saavatech.riserealestate.ui.theme.WelcomeBgTwo

@Composable
fun WelcomeScreen(
    navController: DestinationsNavigator,
    modifier: Modifier = Modifier
){

    Box(
        modifier = modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.welcome_image),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.lighting(WelcomeBgOne, WelcomeBgTwo),
            modifier= Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        listOf(WelcomeBgTwo, WelcomeBgOne)
                    )
                ),
            contentDescription = null)

        //content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier= Modifier
                    .width(320.dp)
                    .height(240.dp),
                contentScale = ContentScale.Fit
            )
            
            Text(text = "Rise",
                fontSize=32.sp,
                fontWeight = FontWeight(700),
                color = Color.White
//                fontFamily = Ale
            )
            Text(text = "RealEstate",
                fontSize=32.sp,
                fontWeight = FontWeight(700),
                color = Color.White
//                fontFamily = Ale
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 20.dp),
        ) {
            ButtonTextComponent(value = "let's Start", clickAction = { navController.navigateTo(Destinations.LoginOption.route) })
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Made with love",
                fontSize = 10.sp,
                fontWeight = FontWeight(200),
                color = ButtonBgOne)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "v.1.0",
                fontSize = 12.sp,
                fontWeight = FontWeight(700),
                color = Color.White)

        }
    }

}

@Preview(showBackground = true)
@Composable
fun WelcomePreview(){
    val navController: NavHostController = rememberNavController()
    val destinationsNavigator = DestinationsNavigator(navController)
    WelcomeScreen(destinationsNavigator)
}
