package com.saavatech.riserealestate.data.repository

import com.saavatech.riserealestate.data.local.AppPreferences
import com.saavatech.riserealestate.data.models.ApiService
import com.saavatech.riserealestate.data.remote.request.SignUpRequest
import com.saavatech.riserealestate.data.remote.response.SignUpResponse
import com.saavatech.riserealestate.domain.repository.AuthRepository
import com.saavatech.riserealestate.util.Resource
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val preferences: AppPreferences,
) : AuthRepository {
    override suspend fun register(signUpRequest: SignUpRequest): Resource<SignUpResponse> =
        try {
            val response = apiService.registerUser(signUpRequest)

            if (response.error) {
                Resource.Error(response.message, response.data)
            }

            if (!response.error) {
                preferences.saveUserData(response.data)
                Resource.Success(response)
            }

            Resource.Success(response)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }
}
