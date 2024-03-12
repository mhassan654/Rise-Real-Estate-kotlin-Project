package com.saavatech.riserealestate.common

sealed class UiState {
    data class SnackbarEvent(val message: String) : UiState()

    data class NavigationEvent(val route: String) : UiState()
}
