package com.saavatech.riserealestate.data.models

import com.saavatech.riserealestate.data.remote.request.SignUpRequest
import com.saavatech.riserealestate.data.remote.response.CategoriesResponse
import com.saavatech.riserealestate.data.remote.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Accept:application/json")
    @POST("/api/register")
    suspend fun registerUser(
        @Body signUpRequest: SignUpRequest,
    ): SignUpResponse

    @Headers("Accept:application/json")
    @GET("/api/get_categories")
    suspend fun getCategories(): CategoriesResponse
}
