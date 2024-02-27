package com.saavatech.riserealestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saavatech.riserealestate.navigation.Destinations
import com.saavatech.riserealestate.presentation.Register.RegisterScreen
import com.saavatech.riserealestate.presentation.SplashViewModel
import com.saavatech.riserealestate.presentation.home.Home
import com.saavatech.riserealestate.presentation.login.LoginScreen
import com.saavatech.riserealestate.presentation.login.LoginScreenOption
import com.saavatech.riserealestate.presentation.onBoarding.OnBoardingScreen
import com.saavatech.riserealestate.presentation.welcome.WelcomeScreen
import com.saavatech.riserealestate.ui.theme.RiseRealEstateTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        setContent {
            RiseRealEstateTheme {
                val screen by splashViewModel.startDestination
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainNavigation(startDestination = screen)
                }
            }
        }
    }
}

@Composable
fun MainNavigation(startDestination: String) {
    val navController: NavHostController = rememberNavController()
    val destinationsNavigator = DestinationsNavigator(navController)

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Destinations.LoginOption.route) {
            LoginScreenOption(destinationsNavigator)
        }

        composable(Destinations.Welcome.route) {
            WelcomeScreen(destinationsNavigator)
        }

        composable(Destinations.Login.route) {
            LoginScreen(destinationsNavigator)
        }

        composable(Destinations.Register.route) {
            RegisterScreen(destinationsNavigator)
        }

        composable(Destinations.OnBoarding.route) {
            OnBoardingScreen(destinationsNavigator)
        }

        composable(Destinations.Home.route) {
            Home()
        }
        // Add other destinations here if needed
    }
}

class DestinationsNavigator(private val navHostController: NavHostController) {
    fun navigateTo(destination: String) {
        navHostController.navigate(destination)
    }

    fun navigateUp() {
        navHostController.popBackStack()
    }
}
