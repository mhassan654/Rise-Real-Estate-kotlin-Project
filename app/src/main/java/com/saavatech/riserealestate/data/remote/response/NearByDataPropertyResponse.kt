package com.saavatech.riserealestate.data.remote.response
import com.google.gson.annotations.SerializedName

data class NearByDataPropertyResponse(
    @SerializedName("data")
    val `data`: List<Property>,
    @SerializedName("error")
    val error: Boolean,
)

data class NearbyPost(
    @SerializedName("category")
    val category: Category,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("property_type")
    val propertyType: String,
    @SerializedName("slug_id")
    val slugId: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_image")
    val titleImage: String,
)
