package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.OkHttpClientProvider
import com.example.data.constants.ACCESS_TOKEN_TAG
import com.example.data.constants.CLIENT_ID_TAG
import com.example.data.constants.HEADER_INTERCEPTOR_TAG
import com.example.data.constants.LANGUAGE_TAG
import com.example.data.constants.LOGGING_INTERCEPTOR_TAG
import com.example.data.interceptors.AUTHORIZATION_HEADER
import com.example.data.interceptors.CLIENT_ID_HEADER
import com.example.data.interceptors.HeaderInterceptor
import com.example.data.okhttp.OkHttpClientProviderInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
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

  @Provides
  @Singleton
  fun provideOkHttpClientProvider(): OkHttpClientProviderInterface {
    return OkHttpClientProvider()
  }

  @Provides
  @Singleton
  fun provideOkHttpCallFactory(
    @Named(LOGGING_INTERCEPTOR_TAG) okHttpLoggingInterceptor: Interceptor,
    @Named(HEADER_INTERCEPTOR_TAG) headerInterceptor: Interceptor,
    okHttpClientProvider: OkHttpClientProviderInterface,
  ): Call.Factory {
    return okHttpClientProvider.getOkHttpClient(BuildConfig.PIN_CERTIFICATE)
      .addInterceptor(okHttpLoggingInterceptor)
      .addInterceptor(headerInterceptor)
      .retryOnConnectionFailure(true)
      .followRedirects(false)
      .followSslRedirects(false)
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .writeTimeout(60, TimeUnit.SECONDS)
      .build()
  }
}
