package com.saavatech.riserealestate.domain.use_case

import com.saavatech.riserealestate.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntryUseCase(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}
