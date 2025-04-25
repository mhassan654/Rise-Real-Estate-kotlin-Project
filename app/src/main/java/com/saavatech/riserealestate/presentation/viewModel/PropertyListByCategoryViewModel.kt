package com.saavatech.riserealestate.presentation.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saavatech.riserealestate.common.UiEvents
import com.saavatech.riserealestate.data.remote.response.Property
import com.saavatech.riserealestate.domain.use_case.PropertyUseCase
import com.saavatech.riserealestate.presentation.LoadingState
import com.saavatech.riserealestate.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PropertyListByCategoryViewModel
    @Inject
    constructor(
        private val savedStateHandle: SavedStateHandle,
        propertyUseCase: PropertyUseCase,
    ) : ViewModel() {
        private val topVillaState: MutableState<List<Property>> = mutableStateOf(emptyList())

        private var _loadingState = mutableStateOf(LoadingState())
        val loadingState: State<LoadingState> = _loadingState
        val propertiesByCategoryListState: MutableState<List<Property>> = mutableStateOf(emptyList())

        private val categoryId: Int = checkNotNull(savedStateHandle["categoryId"])

//        private val userInfo: Flow<UserInfo> = userInfoRepository.getUserInfo(userId)

        init {
            viewModelScope.launch {

                _loadingState.value = loadingState.value.copy(isLoading = true)
                val categoryPropertiesResults = propertyUseCase.categoryProperties(categoryId)
                _loadingState.value = loadingState.value.copy(isLoading = false)

                when (categoryPropertiesResults.result) {
                    is Resource.Success -> {
                        propertiesByCategoryListState.value = categoryPropertiesResults.result.data?.data ?: emptyList()
                    }

                    is Resource.Error -> {
                        Timber.tag("response has an error").d(categoryPropertiesResults.result.message)
                        UiEvents.SnackbarEvent(categoryPropertiesResults.result.message ?: "Error!")
                    }
                    is Resource.Loading -> {
                        TODO()
                    }
                    null -> {
                        TODO()
                    }
                }
                //            Timber.tag("property details").d(propertyState.value.toString())
            }
        }
    }
