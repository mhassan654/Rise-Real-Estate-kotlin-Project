package com.saavatech.riserealestate.presentation.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.saavatech.riserealestate.common.UiEvents
import com.saavatech.riserealestate.data.remote.response.CategoryResponse
import com.saavatech.riserealestate.data.remote.response.Property
import com.saavatech.riserealestate.domain.use_case.CategoriesUseCase
import com.saavatech.riserealestate.domain.use_case.PropertyUseCase
import com.saavatech.riserealestate.presentation.LoadingState
import com.saavatech.riserealestate.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SharedViewModel
    @Inject
    constructor(
        private val categoriesUseCase: CategoriesUseCase,
        private val propertyUseCase: PropertyUseCase,
    ) : ViewModel() {
        private var _loadingState = mutableStateOf(LoadingState())
        val loadingState: State<LoadingState> = _loadingState

        private var _nearbyPropertiesState = mutableStateOf(LoadingState())
        val nearbyPropertiesState: State<LoadingState> = _nearbyPropertiesState

        private val _eventFlow = MutableSharedFlow<UiEvents>()
        val eventFlow = _eventFlow.asSharedFlow()

        val categoriesListState: MutableState<List<CategoryResponse>> = mutableStateOf(emptyList())
        val nearbyPropertiesListState: MutableState<List<Property>> = mutableStateOf(emptyList())

        suspend fun getCategories(): Any =
            try {
                _loadingState.value = loadingState.value.copy(isLoading = true)

                val fetchCategoryResults = categoriesUseCase()

                _loadingState.value = loadingState.value.copy(isLoading = false)

                when (fetchCategoryResults.result) {
                    is Resource.Success ->
                        categoriesListState.value = fetchCategoryResults.result.data?.data ?: emptyList()

                    is Resource.Error -> {
                        Timber.tag("response has an error").d(fetchCategoryResults.result.message)
                        UiEvents.SnackbarEvent(fetchCategoryResults.result.message ?: "Error!")
                    }
                    is Resource.Loading -> TODO()
                    null -> TODO()
                }
            } catch (e: Exception) {
                Timber.tag("error").d(e)
            }

        suspend fun getNearByProperties(): Any =
            try {
                _nearbyPropertiesState.value = nearbyPropertiesState.value.copy(isLoading = true)

                val fetchNearByPropertiesResults = propertyUseCase.nearByProperties()

                _nearbyPropertiesState.value = nearbyPropertiesState.value.copy(isLoading = false)

                when (fetchNearByPropertiesResults.result) {
                    is Resource.Success ->
                        nearbyPropertiesListState.value = fetchNearByPropertiesResults.result.data?.data ?: emptyList()

                    is Resource.Error -> {
                        Timber.tag("response has an error").d(fetchNearByPropertiesResults.result.message)
                        UiEvents.SnackbarEvent(fetchNearByPropertiesResults.result.message ?: "Error!")
                    }
                    is Resource.Loading -> TODO()
                    null -> TODO()
                }
            } catch (e: Exception) {
                Timber.tag("error").d(e)
            }
    }
