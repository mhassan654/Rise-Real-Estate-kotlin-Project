package com.saavatech.riserealestate.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.saavatech.riserealestate.domain.use_case.AppEntryUseCases
import com.saavatech.riserealestate.navigation.Destinations
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainViewModel
    @Inject
    constructor(
        private val appEntryUseCases: AppEntryUseCases,
    ) : ViewModel() {
        private val _splashCondition = mutableStateOf(true)
        val splashCondition: State<Boolean> = _splashCondition

        var startDestination by mutableStateOf(Destinations.Welcome.route)
            private set

        init {
            appEntryUseCases.readAppEntryUseCase().onEach {
            }
        }
    }
