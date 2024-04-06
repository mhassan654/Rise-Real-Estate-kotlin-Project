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
import com.saavatech.riserealestate.presentation.CategoriesState
import com.saavatech.riserealestate.util.Resource
import kotlinx.coroutines.launch
import timber.log.Timber

class PropertyListByCategoryViewModel(
    savedStateHandle: SavedStateHandle,
    propertyUseCase: PropertyUseCase,
) : ViewModel() {
    val topVillaState: MutableState<List<Property>> = mutableStateOf(emptyList())

    private var _categoryPropertiesState = mutableStateOf(CategoriesState())
    val categoryPropertiesState: State<CategoriesState> = _categoryPropertiesState
    val propertiesByCategoryListState: MutableState<List<Property>> = mutableStateOf(emptyList())

    private val categoryId: Int = checkNotNull(savedStateHandle["categoryId"])
    val categoryId2 = savedStateHandle.get<Int>("categoryId") ?: ""

//    private val userInfo: Flow<UserInfo> = userInfoRepository.getUserInfo(userId)

    init {
        viewModelScope.launch {

            Timber.tag("on click property id").d(categoryId.toString())
            val categoryPropertiesResults = propertyUseCase.categoryProperties(categoryId)

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
