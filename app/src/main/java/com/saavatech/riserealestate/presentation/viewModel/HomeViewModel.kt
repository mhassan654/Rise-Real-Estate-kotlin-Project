package com.saavatech.riserealestate.presentation.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.saavatech.riserealestate.common.UiEvents
import com.saavatech.riserealestate.data.remote.response.CategoryResponse
import com.saavatech.riserealestate.domain.use_case.CategoriesUseCase
import com.saavatech.riserealestate.presentation.CategoriesState
import com.saavatech.riserealestate.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(private val categoriesUseCase: CategoriesUseCase) : ViewModel() {
        private var _categoriesState = mutableStateOf(CategoriesState())
        val categoriesState: State<CategoriesState> = _categoriesState

        private val _eventFlow = MutableSharedFlow<UiEvents>()
        val eventFlow = _eventFlow.asSharedFlow()

        val categoriesListState: MutableState<List<CategoryResponse>> = mutableStateOf(emptyList())

        suspend fun getCategories(): Any {
            return try {
                _categoriesState.value = categoriesState.value.copy(isLoading = true)

                delay(3000)

                val fetchCategoryResults = categoriesUseCase()

                _categoriesState.value = categoriesState.value.copy(isLoading = false)

                when (fetchCategoryResults.result) {
                    is Resource.Success ->
                        categoriesListState.value = fetchCategoryResults.result.data?.data ?: emptyList()

                    is Resource.Error -> {
                        UiEvents.SnackbarEvent(fetchCategoryResults.result.message ?: "Error!")
                    }
                    is Resource.Loading -> TODO()
                    null -> TODO()
                }
            } catch (e: Exception) {
                Timber.tag("error").d(e)
            }
        }
    }
