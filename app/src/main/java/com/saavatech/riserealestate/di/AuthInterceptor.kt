package com.saavatech.riserealestate.di

import com.saavatech.riserealestate.data.local.AppPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor
    @Inject
    constructor(
        private val appPreferences: AppPreferences,
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val authToken =
                runBlocking {
                    appPreferences.getAuthToken()
                }

            // modifiy the request as needed
            val request =
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $authToken")
                    .build()

            // proceed with the modified request
            return chain.proceed(request)
        }
    }
