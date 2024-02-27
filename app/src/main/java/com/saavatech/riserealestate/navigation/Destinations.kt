package com.saavatech.riserealestate.navigation

sealed class Destinations(val route: String) {
    object Login : Destinations("login")

    object LoginOption : Destinations("loginOption")

    object Register : Destinations("Register")

    object Home : Destinations("Home")

    object Welcome : Destinations("Welcome")

    object OnBoarding : Destinations("OnBoarding")
    // Define other destinations here
}
