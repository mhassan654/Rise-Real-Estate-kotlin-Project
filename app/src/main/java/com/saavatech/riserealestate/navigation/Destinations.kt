package com.saavatech.riserealestate.navigation

sealed class Destinations(
    val route: String,
) {
    data object Login : Destinations("login")

    data object Register : Destinations("Register")

    data object Home : Destinations("Home")

    data object Welcome : Destinations("Welcome") // Welcome

    data object OnBoarding : Destinations("OnBoarding")

    data object PromotionScreen : Destinations("Promotion")

    data object FeaturedEstate : Destinations("FeaturedEstate")

    data object EstateByCategory : Destinations("EstateByCategory/{categoryId}")

    data object TopLocations : Destinations("TopLocations")

    data object LocationDetails : Destinations("LocationDetails/{location}")

    data object PropertyDetails : Destinations("PropertyDetails/{propertyId}")
    // Define other destinations here
}
