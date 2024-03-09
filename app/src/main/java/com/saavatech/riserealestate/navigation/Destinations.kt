package com.saavatech.riserealestate.navigation

sealed class Destinations(val route: String) {
    data object Login : Destinations("login")

    data object LoginOption : Destinations("loginOption")

    data object Register : Destinations("Register")

    data object Home : Destinations("Home")

    data object Welcome : Destinations("Welcome") // Welcome

    data object OnBoarding : Destinations("OnBoarding")

    data object PromotionScreen : Destinations("Promotion")

    data object FeaturedEstate : Destinations("FeaturedEstate")

    data object EstateByCategory : Destinations("EstateByCategory")

    data object TopLocations : Destinations("TopLocations")
    // Define other destinations here
}
