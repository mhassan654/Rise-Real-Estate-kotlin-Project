package com.saavatech.riserealestate.data.remote.response
import com.google.gson.annotations.SerializedName

data class PropertyDataResponse(
    @SerializedName("data")
    val `data`: List<Property>,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_clicks")
    val totalClicks: Int,
)

data class Property(
    @SerializedName("added_by")
    val addedBy: Int,
    @SerializedName("address")
    val address: String,
    @SerializedName("advertisement")
    val advertisement: List<Advertisement>,
    @SerializedName("assign_facilities")
    val assignFacilities: List<AssignFacility>,
    @SerializedName("category")
    val category: Category,
    @SerializedName("city")
    val city: String,
    @SerializedName("client_address")
    val clientAddress: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("customer_name")
    val customerName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("favourite_users")
    val favouriteUsers: List<Int>,
    @SerializedName("gallery")
    val gallery: List<Gallery>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("inquiry")
    val inquiry: Boolean,
    @SerializedName("interested_users")
    val interestedUsers: List<Int>,
    @SerializedName("is_favourite")
    val isFavourite: Int,
    @SerializedName("is_interested")
    val isInterested: Int,
    @SerializedName("is_reported")
    val isReported: Boolean,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("parameters")
    val parameters: List<Parameter>,
    @SerializedName("post_created")
    val postCreated: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("profile")
    val profile: String,
    @SerializedName("promoted")
    val promoted: Boolean,
    @SerializedName("property_type")
    val propertyType: String,
    @SerializedName("rentduration")
    val rentduration: String,
    @SerializedName("slug_id")
    val slugId: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("threeD_image")
    val threeDImage: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_image")
    val titleImage: String,
    @SerializedName("title_image_hash")
    val titleImageHash: String,
    @SerializedName("total_favourite_users")
    val totalFavouriteUsers: Int,
    @SerializedName("total_interested_users")
    val totalInterestedUsers: Int,
    @SerializedName("total_view")
    val totalView: Int,
    @SerializedName("video_link")
    val videoLink: String,
)

data class Advertisement(
    @SerializedName("category_id")
    val categoryId: Any,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("customer_id")
    val customerId: Int,
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_enable")
    val isEnable: Int,
    @SerializedName("package_id")
    val packageId: Int,
    @SerializedName("property_id")
    val propertyId: Int,
    @SerializedName("slider_id")
    val sliderId: Any,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("title")
    val title: Any,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: String,
)

data class AssignFacility(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("facility_id")
    val facilityId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("property_id")
    val propertyId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
)

data class Gallery(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("image_url")
    val imageUrl: String,
)
