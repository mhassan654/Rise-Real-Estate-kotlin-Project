package com.saavatech.riserealestate.data.models

import com.saavatech.riserealestate.data.remote.request.SignUpRequest
import com.saavatech.riserealestate.data.remote.response.CategoriesResponse
import com.saavatech.riserealestate.data.remote.response.NearByDataPropertyResponse
import com.saavatech.riserealestate.data.remote.response.PropertyDataResponse
import com.saavatech.riserealestate.data.remote.response.SignUpResponse
import com.saavatech.riserealestate.util.ApiConstants.API_GET_CATEGORIES
import com.saavatech.riserealestate.util.ApiConstants.API_GET_PROPERTY
import com.saavatech.riserealestate.util.ApiConstants.API_GET_SYSTEM_SETTINGS
import com.saavatech.riserealestate.util.ApiConstants.API_LOGIN
import com.saavatech.riserealestate.util.ApiConstants.GET_NEARBY_PROPERTIES
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface ApiService {
    @Headers("Accept:application/json")
    @POST(API_LOGIN)
    suspend fun registerUser(
        @Body signUpRequest: SignUpRequest,
    ): SignUpResponse

    @Headers("Accept:application/json")
    @GET(API_GET_CATEGORIES)
    suspend fun getCategories(): CategoriesResponse

    @Headers("Accept:application/json")
    @GET(GET_NEARBY_PROPERTIES)
    suspend fun getNearByProperties(): NearByDataPropertyResponse

    @Headers("Accept:application/json")
    @GET(API_GET_PROPERTY)
    suspend fun getProperties(
        @QueryMap parameters: HashMap<String, Any>,
    ): PropertyDataResponse

    @Headers("Accept:application/json")
    @GET(API_GET_SYSTEM_SETTINGS)
    suspend fun getAppSettings(): NearByDataPropertyResponse
}

// 2. Function to build URL with query parameters
// fun buildGetPropertyQueryParameters(
//    promoted: Boolean?,
//    offset: Int,
//    limit: Int,
//    userId: String? = null,
// ): Map<String, Any?> {
//    val params =
//        mutableMapOf<String, Any?>(
//            ApiConstants.API_PROMOTED to promoted,
//            ApiConstants.API_OFFSET to offset,
//            ApiConstants.API_LIMIT to limit,
//        )
//    if (userId != null) {
//        params["current_user"] = userId
//    }
//    return params.toMap()
// }
