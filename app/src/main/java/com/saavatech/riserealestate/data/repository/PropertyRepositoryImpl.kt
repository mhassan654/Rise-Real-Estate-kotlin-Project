package com.saavatech.riserealestate.data.repository

import com.saavatech.riserealestate.data.models.ApiService
import com.saavatech.riserealestate.data.remote.response.NearByDataPropertyResponse
import com.saavatech.riserealestate.domain.repository.PropertyRepository
import com.saavatech.riserealestate.util.Resource
import retrofit2.HttpException
import java.io.IOException

class PropertyRepositoryImpl(
    private val apiService: ApiService,
) : PropertyRepository {
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
}
