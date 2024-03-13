package com.saavatech.riserealestate.domain.repository

import com.saavatech.riserealestate.data.remote.response.CategoriesResponse
import com.saavatech.riserealestate.util.Resource

interface CategoryRepository {
    suspend fun fetchCategories(): Resource<CategoriesResponse>
}
