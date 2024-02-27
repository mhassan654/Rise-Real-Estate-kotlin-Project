package com.saavatech.riserealestate.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saavatech.riserealestate.data.local.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val appPreferences: AppPreferences):ViewModel() {

    fun saveOnBoardingState(completed:Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            appPreferences.saveOnBoardingState(completed=completed)
        }
    }
}