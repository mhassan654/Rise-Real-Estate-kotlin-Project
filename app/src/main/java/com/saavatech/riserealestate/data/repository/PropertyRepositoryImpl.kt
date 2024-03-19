package com.saavatech.riserealestate.data.repository

import com.saavatech.riserealestate.data.models.ApiService
import com.saavatech.riserealestate.data.remote.response.NearByDataPropertyResponse
import com.saavatech.riserealestate.data.remote.response.PropertyDataResponse
import com.saavatech.riserealestate.domain.model.PropertyQueryParam
import com.saavatech.riserealestate.domain.repository.PropertyRepository
import com.saavatech.riserealestate.util.Resource
import retrofit2.HttpException
import java.io.IOException

class PropertyRepositoryImpl(
    private val apiService: ApiService,
) : PropertyRepository {
//    val promoted = true
//    val offset = 1
//    val limit = 6
    val currentUser = 1

    override suspend fun fetchNearbyProperties(): Resource<NearByDataPropertyResponse> {
        return try {
            val res = apiService.getNearByProperties()
            Resource.Success(res)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }
    }

    override suspend fun fetchFeaturedProperties(
        offset: Int,
        limit: Int,
        promoted: Boolean,
    ): Resource<PropertyDataResponse> {
        val parameters =
            mapOf(
                "promoted" to promoted,
                "offset" to offset,
                "limit" to limit,
                "currentUser" to 1,
            )

        // Assuming you know the types of your parameters
        val promoted: Boolean = true
        val offset = 10
        val limit = 20
        val userId: String? = "1234"

        val queryParams = mutableMapOf<String, PropertyQueryParam>()

        queryParams["promoted"] = PropertyQueryParam("promoted", promoted)
        queryParams["offset"] = PropertyQueryParam("offset", offset)
        queryParams["limit"] = PropertyQueryParam("limit", limit)

        if (userId != null) {
            queryParams["current_user"] = PropertyQueryParam("current_user", userId)
        }
        return try {
            val res =
                apiService.getProperties(queryParams)
            Resource.Success(res)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }
    }
}
