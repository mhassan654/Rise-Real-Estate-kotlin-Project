package com.saavatech.riserealestate.data.remote.response

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("slug_id")
    val slugId: String,
)
