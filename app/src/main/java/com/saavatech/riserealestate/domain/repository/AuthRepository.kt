package com.saavatech.riserealestate.domain.repository

import com.saavatech.riserealestate.data.remote.request.SignUpRequest
import com.saavatech.riserealestate.data.remote.response.SignUpResponse
import com.saavatech.riserealestate.util.Resource

interface AuthRepository {
    suspend fun register(signUpRequest: SignUpRequest): Resource<SignUpResponse>
}
