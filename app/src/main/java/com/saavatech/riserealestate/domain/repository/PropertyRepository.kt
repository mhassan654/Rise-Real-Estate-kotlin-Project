package com.saavatech.riserealestate.domain.repository

import com.saavatech.riserealestate.data.remote.response.NearByDataPropertyResponse
import com.saavatech.riserealestate.data.remote.response.PropertyDataResponse
import com.saavatech.riserealestate.util.Resource

interface PropertyRepository {
    suspend fun fetchNearbyProperties(): Resource<NearByDataPropertyResponse>

//    suspend fun fetchFeaturedProperties(): Resource<PropertyDataResponse>

    suspend fun fetchFeaturedProperties(
        offset: Int,
        limit: Int,
        promoted: Boolean,
    ): Resource<PropertyDataResponse>

    suspend fun fetchCategoryProperties(categoryId: Int): Resource<PropertyDataResponse>

    suspend fun getTopVillaCategoryProperties(): Resource<PropertyDataResponse>
}
