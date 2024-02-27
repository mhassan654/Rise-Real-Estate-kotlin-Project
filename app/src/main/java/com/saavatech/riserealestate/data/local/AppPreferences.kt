package com.saavatech.riserealestate.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.saavatech.riserealestate.util.Constants.ONBOARDING_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class AppPreferences(private val dataStore: DataStore<Preferences>) {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
    }
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }
//    fun readOnBoardingState(): Flow<Boolean> {
//        return dataStore.data
//            .catch { exception ->
//                if (exception is IOException) {
//                    emit(emptyPreferences())
//                } else {
//                    throw exception
//                }
//            }
//            .map { preferences ->
//                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
//                onBoardingState
//            }
//    }
}