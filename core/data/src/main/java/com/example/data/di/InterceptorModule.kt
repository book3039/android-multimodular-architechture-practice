package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.constants.ACCESS_TOKEN_TAG
import com.example.data.constants.CLIENT_ID_TAG
import com.example.data.constants.HEADER_INTERCEPTOR_TAG
import com.example.data.constants.LANGUAGE_TAG
import com.example.data.constants.LOGGING_INTERCEPTOR_TAG
import com.example.data.interceptors.AUTHORIZATION_HEADER
import com.example.data.interceptors.CLIENT_ID_HEADER
import com.example.data.interceptors.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {
  @Provides
  @Singleton
  @Named(HEADER_INTERCEPTOR_TAG)
  fun provideHeaderInterceptor(
    @Named(CLIENT_ID_TAG) clientId: String,
    @Named(ACCESS_TOKEN_TAG) accessTokenProvider: () -> String?,
    @Named(LANGUAGE_TAG) languageProvider: () -> Locale,
  ): Interceptor {
    return HeaderInterceptor(
      clientId = clientId,
      accessTokenProvider = accessTokenProvider,
      languageProvider = languageProvider,
    )
  }

  @Provides
  @Singleton
  @Named(LOGGING_INTERCEPTOR_TAG)
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
}
