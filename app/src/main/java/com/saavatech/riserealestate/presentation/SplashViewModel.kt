package com.saavatech.riserealestate.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saavatech.riserealestate.data.local.AppPreferences
import com.saavatech.riserealestate.navigation.Destinations
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SplashViewModel
    @Inject
    constructor(private val appPreferences: AppPreferences) : ViewModel() {
        private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
        val isLoading: State<Boolean> = _isLoading

        private val _startDestination: MutableState<String> = mutableStateOf(Destinations.OnBoarding.route)
        val startDestination: State<String> = _startDestination

        init {
            viewModelScope.launch {
                appPreferences.readOnBoardingState().collect { completed ->
                    if (completed) {
                        _startDestination.value = Destinations.Welcome.route
                    } else {
                        _startDestination.value = Destinations.OnBoarding.route
                    }
//                    _startDestination.value =
//                        if (completed) {
//                            "Welcome"
//                        } else {
//                            "OnBoarding"
//                        }

                    Timber.d("Start Destination: ${_startDestination.value}")
                }
                _isLoading.value = false
            }
        }
    }
