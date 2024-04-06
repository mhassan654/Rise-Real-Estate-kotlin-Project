package com.saavatech.riserealestate.domain.model

import com.saavatech.riserealestate.data.remote.response.AppSettingsResponse
import com.saavatech.riserealestate.data.remote.response.CategoriesResponse
import com.saavatech.riserealestate.data.remote.response.NearByDataPropertyResponse
import com.saavatech.riserealestate.data.remote.response.PropertyDataResponse
import com.saavatech.riserealestate.util.Resource

data class CategoryResults(
    val result: Resource<CategoriesResponse>? = null,
)

data class PropertyResults(
    val result: Resource<PropertyDataResponse>? = null,
)

data class NearByPropertyResults(
    val result: Resource<NearByDataPropertyResponse>? = null,
)

data class FeaturedPropertyResults(
    val result: Resource<PropertyDataResponse>? = null,
)

data class FetchAppSettingsResults(
    val result: Resource<AppSettingsResponse>? = null,
)

data class PropertyQueryParam(val value: Any)

data class LocationData(
    val latitude: Double,
    val longitude: Double,
    val distance: Double,
)
