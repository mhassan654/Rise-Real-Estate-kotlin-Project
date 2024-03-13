package com.saavatech.riserealestate.data.remote.response
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("data")
    val data: List<CategoryResponse>? = null,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("total")
    val total: Int,
)

data class CategoryResponse(
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("parameter_types")
    val parameterTypes: ParameterTypes,
    @SerializedName("properties_count")
    val propertiesCount: Int,
)

data class ParameterTypes(
    @SerializedName("parameters")
    val parameters: List<Parameter>,
)
