package com.saavatech.riserealestate.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.saavatech.riserealestate.R

sealed class OnBoardingPage(
    val image: Int,
    val title: String,
    val description: String
) {

    data object First : OnBoardingPage(
        image = R.drawable.onboarding_first,
        title = "First title",
        description = "first description"
    )

    data object Second : OnBoardingPage(
        image = R.drawable.onboarding_second,
        title = "Second title",
        description = "second description"
    )

    data object Third : OnBoardingPage(
        image = R.drawable.onboarding_third,
        title = "Third title",
        description = "third description"
    )
}