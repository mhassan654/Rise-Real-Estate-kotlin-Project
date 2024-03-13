package com.saavatech.riserealestate.data.remote.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("email") var email: String,
    @SerializedName("name") var name: String,
    @SerializedName("firebase_id") var firebase_id: String,
    @SerializedName("address") var address: String,
    @SerializedName("mobile") var mobile: String,
    @SerializedName("about_me") var about_me: String?,
    @SerializedName("latitude") var latitude: String?,
    @SerializedName("longitude") var longitude: String?,
    @SerializedName("facebook_id") var facebook_id: String?,
    @SerializedName("twitter_id") var twitter_id: String?,
//    @SerializedName("instagram_id") var instagram_id: String?,
//    @SerializedName("pintrest_id") var pintrest_id: String?,
    @SerializedName("profile") var profile: String?,
)
