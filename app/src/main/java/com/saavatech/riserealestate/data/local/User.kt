package com.saavatech.riserealestate.data.local

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("about_me")
    val aboutMe: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: Any,
    @SerializedName("country")
    val country: Any,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("facebook_id")
    val facebookId: String,
    @SerializedName("fcm_id")
    val fcmId: Any,
    @SerializedName("firebase_id")
    val firebaseId: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("instagram_id")
    val instagramId: String,
    @SerializedName("isActive")
    val isActive: Int,
    @SerializedName("is_premium")
    val isPremium: Int,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("logintype")
    val logintype: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("notification")
    val notification: Int,
    @SerializedName("profile")
    val profile: String,
    @SerializedName("slug_id")
    val slugId: String,
    @SerializedName("state")
    val state: Any,
    @SerializedName("subscription")
    val subscription: Int,
    @SerializedName("twiiter_id")
    val twiiterId: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("youtube_id")
    val youtubeId: String,
)
