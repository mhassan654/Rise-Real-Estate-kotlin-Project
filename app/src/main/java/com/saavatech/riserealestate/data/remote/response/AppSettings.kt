package com.saavatech.riserealestate.data.remote.response
import com.google.gson.annotations.SerializedName

data class AppSettings(
    @SerializedName("data")
    val data: SettingsData,
    @SerializedName("error")
    val error: Boolean,
)

data class SettingsData(
    @SerializedName("app_home_screen")
    val appHomeScreen: String,
    @SerializedName("dark_primary")
    val darkPrimary: String,
    @SerializedName("dark_secondary")
    val darkSecondary: String,
    @SerializedName("dark_tertiary")
    val darkTertiary: String,
    @SerializedName("light_primary")
    val lightPrimary: String,
    @SerializedName("light_secondary")
    val lightSecondary: String,
    @SerializedName("light_tertiary")
    val lightTertiary: String,
    @SerializedName("placeholder_logo")
    val placeholderLogo: String,
    @SerializedName("splash_logo")
    val splashLogo: String,
)
