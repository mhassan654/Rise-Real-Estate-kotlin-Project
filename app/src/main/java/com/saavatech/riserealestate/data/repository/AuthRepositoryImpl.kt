package com.saavatech.riserealestate.data.repository

import com.saavatech.riserealestate.data.local.AppPreferences
import com.saavatech.riserealestate.data.local.EstateSession
import com.saavatech.riserealestate.data.models.ApiService
import com.saavatech.riserealestate.data.remote.request.SignUpRequest
import com.saavatech.riserealestate.domain.repository.AuthRepository
import com.saavatech.riserealestate.util.Resource
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(private val apiService: ApiService,  val session: EstateSession) :
    AuthRepository {
    override suspend fun register(signUpRequest: SignUpRequest): Resource<Unit> {
        return try {
            val response = apiService.registerUser(signUpRequest)
            session.storeToken(response.token)
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }
    }
}
