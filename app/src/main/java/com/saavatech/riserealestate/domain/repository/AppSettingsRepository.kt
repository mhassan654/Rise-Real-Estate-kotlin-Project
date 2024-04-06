package com.saavatech.riserealestate.domain.repository

import com.saavatech.riserealestate.data.remote.response.AppSettingsResponse
import com.saavatech.riserealestate.util.Resource

interface AppSettingsRepository {
    suspend fun getAppSettings(): Resource<AppSettingsResponse>
}
