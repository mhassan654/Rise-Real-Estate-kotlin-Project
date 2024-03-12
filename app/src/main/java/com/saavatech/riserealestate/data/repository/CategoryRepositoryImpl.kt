package com.saavatech.riserealestate.data.repository

import com.saavatech.riserealestate.data.models.ApiService
import com.saavatech.riserealestate.data.remote.response.CategoriesResponse
import com.saavatech.riserealestate.domain.repository.CategoryRepository
import com.saavatech.riserealestate.util.Resource
import retrofit2.HttpException
import java.io.IOException

class CategoryRepositoryImpl(
    private val apiService: ApiService,
) : CategoryRepository {
    override suspend fun fetchCategories(): Resource<CategoriesResponse> {
        return try {
            val res = apiService.getCategories()
            Resource.Success(res)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }
    }
}
