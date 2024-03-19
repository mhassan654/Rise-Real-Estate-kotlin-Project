package com.saavatech.riserealestate.domain.use_case

import com.saavatech.riserealestate.common.UiEvents
import com.saavatech.riserealestate.data.remote.response.NearbyPost
import com.saavatech.riserealestate.data.remote.response.Property
import com.saavatech.riserealestate.domain.model.FeaturedPropertyResults
import com.saavatech.riserealestate.domain.model.NearByPropertyResults
import com.saavatech.riserealestate.domain.repository.PropertyRepository
import com.saavatech.riserealestate.util.Resource
import timber.log.Timber
import javax.inject.Inject

class PropertyUseCase
    @Inject
    constructor(private val repository: PropertyRepository) {
        private var cachedPosts = listOf<NearbyPost>()
        private var cachedFeatureProperties = listOf<Property>()

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

        suspend fun featuredProperties(): FeaturedPropertyResults {
            val featuredPropertiesResults = FeaturedPropertyResults(result = repository.fetchFeaturedProperties(1, 6, true))

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

        fun getProperty(id: Comparable<*>): NearbyPost? {
            return cachedPosts.firstOrNull {
                it.id == id
            }
        }
    }
