package com.saavatech.riserealestate.domain.model

import com.saavatech.riserealestate.data.remote.response.CategoriesResponse
import com.saavatech.riserealestate.util.Resource

data class CategoryResults(
    val result: Resource<CategoriesResponse>? = null,
)
