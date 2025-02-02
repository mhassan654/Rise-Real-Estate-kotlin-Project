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
import com.saavatech.riserealestate.presentation.CategoriesState
import com.saavatech.riserealestate.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val categoriesUseCase: CategoriesUseCase,
        private val propertyUseCase: PropertyUseCase,
    ) : ViewModel() {
        private var _categoriesState = mutableStateOf(CategoriesState())
        val categoriesState: State<CategoriesState> = _categoriesState
        val categoriesListState: MutableState<List<CategoryResponse>> = mutableStateOf(emptyList())

        // nearby properties
        private var _nearbyPropertiesState = mutableStateOf(CategoriesState())
        val nearbyPropertiesState: State<CategoriesState> = _nearbyPropertiesState
        val nearbyPropertiesListState: MutableState<List<Property>> = mutableStateOf(emptyList())

        // featured properties
        private var _featuredPropertiesState = mutableStateOf(CategoriesState())
        val featuredPropertiesState: State<CategoriesState> = _featuredPropertiesState
        val featuredPropertiesListState: MutableState<List<Property>> = mutableStateOf(emptyList())

        private val _eventFlow = MutableSharedFlow<UiEvents>()
        val eventFlow = _eventFlow.asSharedFlow()

        fun getProperty(id: Int): Property? {
            // Access the current list from the state
            val nearbyProperties = nearbyPropertiesListState.value

            return nearbyProperties.firstOrNull { it.id == id }
        }

        fun getProperty2(id: Int): Property? {
            return nearbyPropertiesListState.value.filter { it.id == id }.firstOrNull()
        }

        suspend fun getCategories(): Any {
            return try {
                _categoriesState.value = categoriesState.value.copy(isLoading = true)

                val fetchCategoryResults = categoriesUseCase()

                _categoriesState.value = categoriesState.value.copy(isLoading = false)

                when (fetchCategoryResults.result) {
                    is Resource.Success -> {
                        categoriesListState.value = fetchCategoryResults.result.data?.data ?: emptyList()
                    }

                    is Resource.Error -> {
                        Timber.tag("response has an error").d(fetchCategoryResults.result.message)
                        UiEvents.SnackbarEvent(fetchCategoryResults.result.message ?: "Error!")
                    }
                    is Resource.Loading -> {
                        TODO()
                    }
                    null -> {
                        TODO()
                    }
                }
            } catch (e: Exception) {
                Timber.tag("error").d(e)
            }
        }

        suspend fun getNearByProperties(): Any {
            return try {
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

        suspend fun getFeaturedProperties(
            offset: Int,
            limit: Int,
            promoted: Boolean,
        ): Any {
            return try {
                _featuredPropertiesState.value = featuredPropertiesState.value.copy(isLoading = true)

                val fetchFeaturedPropertiesResults = propertyUseCase.featuredProperties(offset, limit, promoted)

                _nearbyPropertiesState.value = nearbyPropertiesState.value.copy(isLoading = false)
                Timber.tag("featured properties").d(featuredPropertiesListState.value.toString())
                when (fetchFeaturedPropertiesResults.result) {
                    is Resource.Success ->
                        featuredPropertiesListState.value = fetchFeaturedPropertiesResults.result.data?.data ?: emptyList()

                    is Resource.Error,
                    -> {
                        Timber.tag("response has an error").d(fetchFeaturedPropertiesResults.result.message)
                        UiEvents.SnackbarEvent(fetchFeaturedPropertiesResults.result.message ?: "Error!")
                    }
                    is Resource.Loading -> TODO()
                    null -> TODO()
                }
            } catch (e: Exception) {
                Timber.tag("error").d(e)
            }
        }
    }
