package com.saavatech.riserealestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saavatech.riserealestate.presentation.Register.RegisterScreen
import com.saavatech.riserealestate.presentation.login.LoginScreen
import com.saavatech.riserealestate.presentation.login.LoginScreenOption
import com.saavatech.riserealestate.presentation.onBoarding.OnBoardingPage
import com.saavatech.riserealestate.presentation.onBoarding.OnBoardingScreen
import com.saavatech.riserealestate.presentation.welcome.WelcomeScreen
import com.saavatech.riserealestate.ui.theme.RiseRealEstateTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RiseRealEstateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RiseRealEstateTheme {
        Greeting("Android")
    }
}


@Composable
fun MainNavigation(){
    val navController: NavHostController = rememberNavController()
    val destinationsNavigator = DestinationsNavigator(navController)

    NavHost(navController = navController, startDestination = Destinations.OnBoarding.route) {
        composable(Destinations.LoginOption.route) {
            LoginScreenOption(destinationsNavigator)
        }

        composable(Destinations.Welcome.route){
            WelcomeScreen(destinationsNavigator)
        }

        composable(Destinations.Login.route){
            LoginScreen(destinationsNavigator)
        }
//
        composable(Destinations.Register.route){
            RegisterScreen(destinationsNavigator)
        }

        composable(Destinations.OnBoarding.route){
            OnBoardingScreen(destinationsNavigator)
        }
        // Add other destinations here if needed
    }

}

class DestinationsNavigator(private val navHostController: NavHostController){
    fun navigateTo(destination: String){
        navHostController.navigate(destination)
    }

    fun navigateUp(){
        navHostController.popBackStack()
    }
}

sealed class Destinations(val route: String) {
    data object Login : Destinations("login")
    data object LoginOption : Destinations("loginOption")
    data object Register : Destinations("Register")
    data object Home : Destinations("Home")
    data object Welcome : Destinations("Welcome")
    data object OnBoarding : Destinations("OnBoarding")
    // Define other destinations here
}