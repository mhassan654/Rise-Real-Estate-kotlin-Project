package com.saavatech.riserealestate.presentation.onBoarding

import com.saavatech.riserealestate.R

sealed class OnBoardingPage(
    val image: Int,
    val title1: String,
    val title2: String,
    val title3: String?,
    val description: String,
) {
    data object First : OnBoardingPage(
        image = R.drawable.onboarding_first,
        title1 = "Find best place",
        title2 = "to stay in ",
        title3 = "good place ",
        description = "Discover a wide variety of places that suit your needs and preferences. Find best place to stay in good place",
    )

    data object Second : OnBoardingPage(
        image = R.drawable.onboarding_second,
        title1 = "Fast sell your property",
        title2 = "in just",
        title3 = "one click",
        description = "Discover a wide variety of places that suit your needs and preferences. Fast sell your property in just one click",
    )

    data object Third : OnBoardingPage(
        image = R.drawable.onboarding_third,
        title1 = "Find ",
        title2 = "your future house",
        title3 = "perfect choice",
        description = "Discover a wide variety of places that suit your needs and preferences. Find your future house perfect choice",
    )
}
