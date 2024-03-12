package com.saavatech.riserealestate.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.saavatech.riserealestate.data.local.AppPreferences
import com.saavatech.riserealestate.data.models.ApiService
import com.saavatech.riserealestate.data.repository.AuthRepositoryImpl
import com.saavatech.riserealestate.domain.repository.AuthRepository
import com.saavatech.riserealestate.util.Constants.AUTH_PREFERENCES
import com.saavatech.riserealestate.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePreferenceDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(AUTH_PREFERENCES) },
        )

    @Provides
    @Singleton
    fun provideAppPreferences(dataStore: DataStore<Preferences>) = AppPreferences(dataStore)

    @Provides
    @Singleton
    fun providesApiService(authInterceptor: AuthInterceptor): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client =
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesAuthRepository(
        apiService: ApiService,
        preferences: AppPreferences,
    ): AuthRepository {
        return AuthRepositoryImpl(apiService = apiService, preferences = preferences)
    }
}
