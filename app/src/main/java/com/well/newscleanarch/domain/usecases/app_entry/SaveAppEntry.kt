package com.well.newscleanarch.domain.usecases.app_entry

import com.well.newscleanarch.domain.manager.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}