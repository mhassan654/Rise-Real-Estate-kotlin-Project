package com.saavatech.riserealestate.domain.repository

import com.saavatech.riserealestate.data.remote.response.NearByDataPropertyResponse
import com.saavatech.riserealestate.util.Resource

interface PropertyRepository {
    suspend fun fetchNearbyProperties(): Resource<NearByDataPropertyResponse>
}
