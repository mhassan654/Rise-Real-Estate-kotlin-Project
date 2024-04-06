package com.saavatech.riserealestate.data.repository

import com.saavatech.riserealestate.data.models.ApiService
import com.saavatech.riserealestate.data.remote.response.AppSettingsResponse
import com.saavatech.riserealestate.domain.repository.AppSettingsRepository
import com.saavatech.riserealestate.util.Resource
import retrofit2.HttpException
import java.io.IOException

class AppSettingsRepositoryImpl(private val apiService: ApiService) : AppSettingsRepository {
    override suspend fun getAppSettings(): Resource<AppSettingsResponse> {
        return try {
            val res = apiService.getAppSettings()
            Resource.Success(res)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }
    }
}
