package com.saavatech.riserealestate.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.saavatech.riserealestate.data.local.AppPreferences
import com.saavatech.riserealestate.util.Constants.AUTH_PREFERENCES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create(
        produceFile = {context.preferencesDataStoreFile(AUTH_PREFERENCES)}
    )

    @Provides
    @Singleton
    fun provideAppPreferences(dataStore: DataStore<Preferences>)=AppPreferences(dataStore)
}