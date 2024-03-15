package com.saavatech.riserealestate.common

sealed class OnBoardingEvent {
    data object SaveAppEntry : OnBoardingEvent()
}
