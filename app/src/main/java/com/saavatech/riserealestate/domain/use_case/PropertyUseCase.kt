package com.saavatech.riserealestate.domain.use_case

import com.saavatech.riserealestate.common.UiEvents
import com.saavatech.riserealestate.data.remote.response.Property
import com.saavatech.riserealestate.domain.model.FeaturedPropertyResults
import com.saavatech.riserealestate.domain.model.NearByPropertyResults
import com.saavatech.riserealestate.domain.model.PropertyResults
import com.saavatech.riserealestate.domain.repository.PropertyRepository
import com.saavatech.riserealestate.util.Resource
import timber.log.Timber
import javax.inject.Inject

class PropertyUseCase
    @Inject
    constructor(private val repository: PropertyRepository) {
        private var cachedPosts = listOf<Property>()
        private var cachedFeatureProperties = listOf<Property>()
        private var catProperties = listOf<Property>()

        suspend fun nearByProperties(): NearByPropertyResults {
            val fetchNearByPropertiesResults = NearByPropertyResults(result = repository.fetchNearbyProperties())

            when (fetchNearByPropertiesResults.result) {
                is Resource.Success ->
                    cachedPosts = fetchNearByPropertiesResults.result.data?.data ?: emptyList()

                is Resource.Error -> {
                    Timber.tag("response has an error").d(fetchNearByPropertiesResults.result.message)
                    UiEvents.SnackbarEvent(fetchNearByPropertiesResults.result.message ?: "Error!")
                }
                is Resource.Loading -> TODO()
                null -> TODO()
            }

            return fetchNearByPropertiesResults
        }

        suspend fun featuredProperties(
            offset: Int,
            limit: Int,
            promoted: Boolean,
        ): FeaturedPropertyResults {
            val featuredPropertiesResults = FeaturedPropertyResults(result = repository.fetchFeaturedProperties(offset, limit, promoted))

            when (featuredPropertiesResults.result) {
                is Resource.Success ->
                    cachedFeatureProperties = featuredPropertiesResults.result.data?.data ?: emptyList()

                is Resource.Error -> {
                    Timber.tag("response has an error").d(featuredPropertiesResults.result.message)
                    UiEvents.SnackbarEvent(featuredPropertiesResults.result.message ?: "Error!")
                }
                is Resource.Loading -> TODO()
                null -> TODO()
            }

            return featuredPropertiesResults
        }

        suspend fun categoryProperties(categoryId: Int): PropertyResults {
            val categoryPropertiesResults = PropertyResults(result = repository.fetchCategoryProperties(categoryId))

            when (categoryPropertiesResults.result) {
                is Resource.Success ->
                    catProperties = categoryPropertiesResults.result.data?.data ?: emptyList()

                is Resource.Error -> {
                    Timber.tag("response has an error").d(categoryPropertiesResults.result.message)
                    UiEvents.SnackbarEvent(categoryPropertiesResults.result.message ?: "Error!")
                }
                is Resource.Loading -> TODO()
                null -> TODO()
            }

            return categoryPropertiesResults
        }

        fun getPropertyNearby(id: Comparable<*>): Property? {
            return cachedPosts.firstOrNull {
                it.id == id
            }
        }

        fun getProperty(id: Comparable<*>): Property? {
            return cachedFeatureProperties.firstOrNull {
                it.id == id
            }
        }
    }
