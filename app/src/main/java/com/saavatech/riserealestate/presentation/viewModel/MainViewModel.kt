package com.saavatech.riserealestate.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saavatech.riserealestate.domain.use_case.AppEntryUseCases
import com.saavatech.riserealestate.navigation.Destinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val appEntryUseCases: AppEntryUseCases,
    ) : ViewModel() {
        var splashCondition by mutableStateOf(true)
            private set
//        var splashCondition: State<Boolean> = _splashCondition

        var startDestination by mutableStateOf(Destinations.OnBoarding.route)
            private set // ///////////////////////////////////////////

        init {
            appEntryUseCases.readAppEntryUseCase().onEach {
                    shouldStartFromHomeScreen ->
                Timber.tag("should start from home screen").d(shouldStartFromHomeScreen.toString())
                startDestination =
                    if (shouldStartFromHomeScreen) {
                        Destinations.Welcome.route
                    } else {
                        Destinations.OnBoarding.route
                    }
                delay(3000)
                splashCondition = false
            }.launchIn(viewModelScope)
        }
    }
