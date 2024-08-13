package com.saavatech.riserealestate.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.google.gson.Gson
import com.saavatech.riserealestate.util.Constants
import com.saavatech.riserealestate.util.Constants.APP_ENTRY
import com.saavatech.riserealestate.util.Constants.AUTH_KEY
import com.saavatech.riserealestate.util.Constants.USER_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException

class AppPreferences(
    private val dataStore: DataStore<Preferences>,
) {
    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
        val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    suspend fun saveAuthToken(token: String) {
        dataStore.edit { pref ->
            pref[AUTH_KEY] = setOf(token)
        }
    }

    suspend fun saveUserData(user: User) {
        val gson = Gson()
        val userJson = gson.toJson(user)
        dataStore.edit { pref ->
            pref[USER_KEY] = setOf(userJson)
        }
    }

    suspend fun getUserData(): User? {
        val gson = Gson()
        val userJson =
            dataStore.data
                .map { preferences ->
                    preferences[USER_KEY]
                }.firstOrNull()

        return userJson?.let { gson.fromJson(it, User::class.java) } ?: null
    }

    suspend fun getAuthToken(): String? = dataStore.data.first()[AUTH_KEY]?.firstOrNull()

    fun readOnBoardingState(): Flow<Boolean> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
}
