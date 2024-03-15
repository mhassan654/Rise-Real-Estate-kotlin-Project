package com.saavatech.riserealestate.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saavatech.riserealestate.common.OnBoardingEvent
import com.saavatech.riserealestate.domain.use_case.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel
    @Inject
    constructor(private val appEntryUseCases: AppEntryUseCases) : ViewModel() {
        fun onEvent(event: OnBoardingEvent) {
            when (event) {
                is OnBoardingEvent.SaveAppEntry -> {
                    saveAppEntry()
                }
            }
        }

        private fun saveAppEntry()  {
            viewModelScope.launch {
                appEntryUseCases.saveAppEntryUseCase()
            }
        }
    }
