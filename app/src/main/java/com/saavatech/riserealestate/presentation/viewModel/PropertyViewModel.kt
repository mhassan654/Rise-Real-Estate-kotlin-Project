package com.saavatech.riserealestate.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saavatech.riserealestate.data.remote.response.Property
import com.saavatech.riserealestate.domain.use_case.PropertyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel
    @Inject
    constructor(
        private val savedStateHandle: SavedStateHandle,
        private val propertyUseCase: PropertyUseCase,
    ) : ViewModel() {
        var propertyState = mutableStateOf<Property?>(null)

        init {
            viewModelScope.launch {
                val propertyId = savedStateHandle.get<Int>("propertyId") ?: ""
                Timber.tag("on click property id").d(propertyId.toString())
                propertyState.value = propertyUseCase.getProperty(propertyId)
                Timber.tag("property details").d(propertyState.value.toString())
            }
        }
    }
