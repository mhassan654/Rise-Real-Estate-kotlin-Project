package com.saavatech.riserealestate.domain.use_case

import com.saavatech.riserealestate.common.UiEvents
import com.saavatech.riserealestate.data.remote.response.NearbyPost
import com.saavatech.riserealestate.domain.model.NearByPropertyResults
import com.saavatech.riserealestate.domain.repository.PropertyRepository
import com.saavatech.riserealestate.util.Resource
import timber.log.Timber
import javax.inject.Inject

class PropertyUseCase
    @Inject
    constructor(private val repository: PropertyRepository) {
        private var cachedPosts = listOf<NearbyPost>()

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

        fun getProperty(id: Comparable<*>): NearbyPost? {
            return cachedPosts.firstOrNull {
                it.id == id
            }
        }
    }
