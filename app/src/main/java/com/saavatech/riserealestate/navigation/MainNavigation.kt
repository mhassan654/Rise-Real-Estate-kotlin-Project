package com.saavatech.riserealestate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saavatech.riserealestate.DestinationsNavigator
import com.saavatech.riserealestate.presentation.Register.RegisterScreen
import com.saavatech.riserealestate.presentation.home.Home
import com.saavatech.riserealestate.presentation.login.LoginScreen
import com.saavatech.riserealestate.presentation.login.LoginScreenOption
import com.saavatech.riserealestate.presentation.onBoarding.OnBoardingScreen
import com.saavatech.riserealestate.presentation.welcome.WelcomeScreen
import timber.log.Timber

@Composable
fun MainNavigation(
    navController: NavHostController,
    startScreen: String,
) {
    val destinationsNavigator = DestinationsNavigator(navController)
    Timber.tag("start screen").d(startScreen)

    NavHost(navController = navController, startDestination = startScreen) {
        composable(route = Destinations.LoginOption.route) {
            LoginScreenOption(destinationsNavigator)
        }

        composable(route = Destinations.Welcome.route) {
            WelcomeScreen(destinationsNavigator)
        }

        composable(route = Destinations.Login.route) {
            LoginScreen(destinationsNavigator)
        }

        composable(route = Destinations.Register.route) {
            RegisterScreen(destinationsNavigator)
        }

        composable(route = Destinations.OnBoarding.route) {
            OnBoardingScreen(destinationsNavigator)
        }

        composable(BottomScreens.Home.route) {
            Home()
        }
        // Add other destinations here if needed
    }
}

fun isRouteInGraph(
    navController: NavHostController,
    route: String,
): Boolean {
    val navGraph: NavGraph = navController.graph
    return navGraph.findNode(route) != null
}
