package com.saavatech.riserealestate.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.saavatech.riserealestate.data.local.AppPreferences
import com.saavatech.riserealestate.data.local.LocalUsermanagerImpl
import com.saavatech.riserealestate.data.models.ApiService
import com.saavatech.riserealestate.data.repository.AuthRepositoryImpl
import com.saavatech.riserealestate.data.repository.CategoryRepositoryImpl
import com.saavatech.riserealestate.data.repository.PropertyRepositoryImpl
import com.saavatech.riserealestate.domain.manager.LocalUserManager
import com.saavatech.riserealestate.domain.repository.AuthRepository
import com.saavatech.riserealestate.domain.repository.CategoryRepository
import com.saavatech.riserealestate.domain.repository.PropertyRepository
import com.saavatech.riserealestate.domain.use_case.AppEntryUseCases
import com.saavatech.riserealestate.domain.use_case.CategoriesUseCase
import com.saavatech.riserealestate.domain.use_case.PropertyUseCase
import com.saavatech.riserealestate.domain.use_case.ReadAppEntryUseCase
import com.saavatech.riserealestate.domain.use_case.SaveAppEntryUseCase
import com.saavatech.riserealestate.domain.use_case.SignUpUseCase
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
    fun provideLocalUserManager(application: Application): LocalUserManager = LocalUsermanagerImpl(application)

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
//            .addConverterFactory(PropertyQueryParamConverterFactory())
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

    @Provides
    @Singleton
    fun providesSignUpUseCase(repository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesCategoryUseCase(repository: CategoryRepository): CategoriesUseCase {
        return CategoriesUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesCategoryRepository(apiService: ApiService): CategoryRepository {
        return CategoryRepositoryImpl(apiService = apiService)
    }

    @Provides
    @Singleton
    fun providesPropertyUseCase(repository: PropertyRepository): PropertyUseCase {
        return PropertyUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesPropertyRepository(apiService: ApiService): PropertyRepository {
        return PropertyRepositoryImpl(apiService = apiService)
    }

    @Provides
    @Singleton
    fun providesAppEntryUseCases(localUserManager: LocalUserManager) =
        AppEntryUseCases(
            readAppEntryUseCase = ReadAppEntryUseCase(localUserManager),
            saveAppEntryUseCase = SaveAppEntryUseCase(localUserManager),
        )
}
