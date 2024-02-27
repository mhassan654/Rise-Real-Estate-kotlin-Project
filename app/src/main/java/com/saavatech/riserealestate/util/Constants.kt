package com.saavatech.riserealestate.util

import androidx.datastore.preferences.core.stringSetPreferencesKey

object Constants {
    const val BASE_URL ="http://192.168.0.118:8000"
    const val AUTH_PREFERENCES = "AUTH_PREF"
    val AUTH_KEY = stringSetPreferencesKey("auth_key")
    val ONBOARDING_KEY = stringSetPreferencesKey("onboarding_key")
}