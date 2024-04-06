package com.saavatech.riserealestate.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.saavatech.riserealestate.DestinationsNavigator
import com.saavatech.riserealestate.presentation.FeaturedList.FeaturedEstate
import com.saavatech.riserealestate.presentation.FeaturedList.RealEstateListByCategory
import com.saavatech.riserealestate.presentation.FeaturedList.TopLocations.LocationDetails
import com.saavatech.riserealestate.presentation.FeaturedList.TopLocations.TopLocationsScreen
import com.saavatech.riserealestate.presentation.PromotionScreen
import com.saavatech.riserealestate.presentation.Register.RegisterScreen
import com.saavatech.riserealestate.presentation.details.PropertyDetails
import com.saavatech.riserealestate.presentation.home.Home
import com.saavatech.riserealestate.presentation.login.LoginScreen
import com.saavatech.riserealestate.presentation.login.LoginScreenOption
import com.saavatech.riserealestate.presentation.onBoarding.OnBoardingScreen
import com.saavatech.riserealestate.presentation.viewModel.OnBoardingViewModel
import com.saavatech.riserealestate.presentation.viewModel.PropertyViewModel
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
            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen(event = viewModel::onEvent)
        }

        composable(route = Destinations.PromotionScreen.route) {
            PromotionScreen(destinationsNavigator)
        }

        composable(BottomScreens.Home.route) {
                backStackEntry ->
            val location = backStackEntry.arguments?.getInt("propertyId")
            Home(destinationsNavigator) {
                    propertyDetailsId ->
                destinationsNavigator.navigateTo("PropertyDetails/$location")
            }
        }

        composable(Destinations.FeaturedEstate.route) { backStackEntry ->
            val location = backStackEntry.arguments?.getInt("propertyId")
            FeaturedEstate(destinationsNavigator)
        }

        composable(
            Destinations.EstateByCategory.route,
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType }),
        ) {
            RealEstateListByCategory(
                destinationsNavigator,
            )
        }

        composable(Destinations.TopLocations.route) { backStackEntry ->
            val location = backStackEntry.arguments?.getInt("location")
            TopLocationsScreen(destinationsNavigator) {
                    locationDetailsId ->
                destinationsNavigator.navigateTo("LocationDetails/$locationDetailsId")
            }
        }

        composable(
            Destinations.LocationDetails.route,
            arguments = listOf(navArgument("location") { type = NavType.IntType }),
        ) {
            LocationDetails {
                    locationDetailsId ->
                destinationsNavigator.navigateTo("PropertyDetails/$locationDetailsId")
            }
        }

        composable(
            Destinations.PropertyDetails.route,
            arguments = listOf(navArgument("propertyId") { type = NavType.IntType }),
        ) {
            val propertyViewModel: PropertyViewModel = hiltViewModel()
            PropertyDetails(propertyViewModel.propertyState.value, destinationsNavigator)
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
