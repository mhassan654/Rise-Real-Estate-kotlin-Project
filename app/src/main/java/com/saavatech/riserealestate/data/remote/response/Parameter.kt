package com.saavatech.riserealestate.data.remote.response

import com.google.gson.annotations.SerializedName

data class Parameter(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type_of_parameter")
    val typeOfParameter: String,
    @SerializedName("type_values")
    val typeValues: Any?,
)
