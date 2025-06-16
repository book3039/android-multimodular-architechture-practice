package com.example.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    @Named("Language")
    fun provideLanguage(): () -> Locale {
        return { Locale.ENGLISH } // to do, get locale from user prefs later
    }

    @Provides
    @Singleton
    @Named("AccessToken")
    fun provideAccessToken(): () -> String? {
        return { "" } // to do, get access token from user prefs later
    }

    @Provides
    @Singleton
    @Named("ClientId")
    fun provideClientId(): String {
        return "" // to do, get client id from user prefs later
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(
        @Named("ClientId") clientId: String,
        @Named("AccessToken") accessTokenProvider: () -> String?,
        @Named("Language") languageProvider: () -> Locale
    ): Interceptor {
        return HeaderInterceptor(
            clientId = clientId,
            accessTokenProvider = accessTokenProvider,
            languageProvider = languageProvider
        )
    }

    @Provides
    @Singleton
    fun provideOkHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            } else {
                level = HttpLoggingInterceptor.Level.NONE
                redactHeader(CLIENT_ID_HEADER)
                redactHeader(AUTHORIZATION_HEADER)
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpCallFactory(interceptor: Interceptor): Call.Factory {
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}
