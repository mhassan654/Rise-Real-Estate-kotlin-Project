package com.saavatech.riserealestate.domain.use_case

import com.saavatech.riserealestate.domain.manager.LocalUserManager

class SaveAppEntryUseCase(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}
