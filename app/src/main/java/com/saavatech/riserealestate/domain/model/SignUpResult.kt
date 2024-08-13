package com.saavatech.riserealestate.domain.model

import com.saavatech.riserealestate.data.remote.response.SignUpResponse
import com.saavatech.riserealestate.util.Resource

data class SignUpResult(
    val nameError: String? = null,
    val addressError: String? = null,
    val passwordError: String? = null,
    val emailError: String? = null,
    val mobileError: String? = null,
    val result: Resource<SignUpResponse>? = null,
)

data class Data(
    val about_me: String,
    val address: String,
    val created_at: String,
    val customertotalpost: Int,
    val email: String,
    val facebook_id: String,
    val fcm_id: Any,
    val firebase_id: String,
    val id: Int,
    val instagram_id: String,
    val isActive: Int,
    val latitude: String,
    val logintype: String,
    val longitude: String,
    val mobile: String,
    val name: String,
    val notification: Int,
    val pintrest_id: String,
    val profile: String,
    val subscription: Int,
    val twiiter_id: String,
    val updated_at: String,
)
