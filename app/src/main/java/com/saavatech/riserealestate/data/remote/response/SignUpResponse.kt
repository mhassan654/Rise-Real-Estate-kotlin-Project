package com.saavatech.riserealestate.data.remote.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("error")
    val error: Boolean = false,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("token")
    val token: String = "",
)

data class Data(
    @SerializedName("address")
    val address: String = "",
    @SerializedName("profile")
    val profile: String = "",
    @SerializedName("latitude")
    val latitude: String = "",
    @SerializedName("firebase_id")
    val firebaseId: String = "",
    @SerializedName("mobile")
    val mobile: String = "",
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("subscription")
    val subscription: Int = 0,
    @SerializedName("twiiter_id")
    val twiiterId: String = "",
    @SerializedName("isActive")
    val isActive: Int = 0,
    @SerializedName("about_me")
    val aboutMe: String = "",
    @SerializedName("facebook_id")
    val facebookId: String = "",
    @SerializedName("instagram_id")
    val instagramId: String = "",
    @SerializedName("pintrest_id")
    val pintrestId: String = "",
    @SerializedName("notification")
    val notification: Int = 0,
    @SerializedName("updated_at")
    val updatedAt: String = "",
    @SerializedName("logintype")
    val logintype: String = "",
    @SerializedName("customertotalpost")
    val customertotalpost: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("email")
    val email: String = "",
//    @SerializedName("fcm_id")
//    val fcmId: Null = null,
    @SerializedName("longitude")
    val longitude: String = "",
)
