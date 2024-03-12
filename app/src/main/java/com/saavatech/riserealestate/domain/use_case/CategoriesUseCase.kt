package com.saavatech.riserealestate.domain.use_case

import com.saavatech.riserealestate.domain.model.CategoryResults
import com.saavatech.riserealestate.domain.repository.CategoryRepository

class CategoriesUseCase(private val repository: CategoryRepository) {
    suspend operator fun invoke(): CategoryResults  {
        return CategoryResults(result = repository.fetchCategories())
    }
}
