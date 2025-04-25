package com.saavatech.riserealestate.domain.use_case

import com.saavatech.riserealestate.domain.model.FetchAppSettingsResults
import com.saavatech.riserealestate.domain.repository.AppSettingsRepository
import timber.log.Timber
import javax.inject.Inject

class AppSettingsUseCase
    @Inject
    constructor(private val appSettingsRepository: AppSettingsRepository) {
        suspend fun getAppSettings() {
            val fetchSettings = FetchAppSettingsResults(result = appSettingsRepository.getAppSettings())
            Timber.tag("app settings").d(fetchSettings.toString())
        }
    }
