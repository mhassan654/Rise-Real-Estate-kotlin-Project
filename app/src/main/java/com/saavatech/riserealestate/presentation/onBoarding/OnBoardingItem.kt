package com.saavatech.riserealestate.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.saavatech.riserealestate.R

sealed class OnBoardingPage(
    val image: Int,
    val title1: String,
    val title2: String,
    val title3: String?,
    val description: String
) {

    data object First : OnBoardingPage(
        image = R.drawable.onboarding_first,
        title1 = "Find best place",
        title2 = "to stay in ",
        title3 = "good place ",
        description = "first description"
    )

    data object Second : OnBoardingPage(
        image = R.drawable.onboarding_second,
        title1 = "Fast sell your property",
        title2 = "in just",
        title3 = "one click",
        description = "first description"
    )

    data object Third : OnBoardingPage(
        image = R.drawable.onboarding_third,
        title1 = "Find ",
        title2 = "your future house",
        title3 = "perfect choice",
        description = "first description"
    )
}