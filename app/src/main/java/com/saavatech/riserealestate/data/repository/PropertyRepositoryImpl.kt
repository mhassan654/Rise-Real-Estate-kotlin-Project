package com.saavatech.riserealestate.data.repository

import com.saavatech.riserealestate.data.local.AppPreferences
import com.saavatech.riserealestate.data.models.ApiService
import com.saavatech.riserealestate.data.remote.response.NearByDataPropertyResponse
import com.saavatech.riserealestate.data.remote.response.PropertyDataResponse
import com.saavatech.riserealestate.domain.repository.PropertyRepository
import com.saavatech.riserealestate.util.Resource
import retrofit2.HttpException
import java.io.IOException

class PropertyRepositoryImpl(
    private val apiService: ApiService,
    private val appPreferences: AppPreferences,
) : PropertyRepository {
//    val promoted = true
//    val offset = 1
//    val limit = 6

    override suspend fun fetchNearbyProperties(): Resource<NearByDataPropertyResponse> =
        try {
            val res = apiService.getNearByProperties()
            Resource.Success(res)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }

    override suspend fun fetchFeaturedProperties(
        offset: Int,
        limit: Int,
        promoted: Boolean,
    ): Resource<PropertyDataResponse> {
        val getUser = appPreferences.getUserData()
        val currentUser = getUser?.id

        val parameters =
            HashMap<String, Any>()
        parameters["promoted"] = promoted
        parameters["offset"] = offset
        parameters["limit"] = limit
        currentUser.also {
            if (it != null) {
                parameters["currentUser"] = it.toInt()
            }
        }

        return try {
            val res =
                apiService.getProperties(parameters)
            Resource.Success(res)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }
    }

    override suspend fun fetchCategoryProperties(categoryId: Int): Resource<PropertyDataResponse> =
        try {
            val res =
                apiService.getCategoryProperties(categoryId)
            Resource.Success(res)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }

    override suspend fun getTopVillaCategoryProperties(): Resource<PropertyDataResponse> {
        TODO("Not yet implemented")
    }
}
