package com.sample.noti.core.ocr.di

import com.sample.noti.core.ocr.service.OcrService
import com.sample.noti.core.ui.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_OCR_SERVER_URl = "https://vision.googleapis.com/"
private const val MAX_TIME_OUT_MILLS = 10_000L

private val jsonRule = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    prettyPrint = true
}

private val jsonConverterFactory = jsonRule.asConverterFactory("application/json".toMediaType())

@Module
@InstallIn(SingletonComponent::class)
object OcrNetworkModule {

    @Provides
    @Singleton
    @OcrOkHttp
    fun provideOkHttp(): OkHttpClient {
        val log = HttpLoggingInterceptor().apply {
            redactHeader("X-Goog-Api-Key")
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        return OkHttpClient.Builder()
            .addInterceptor(log)
            .connectTimeout(MAX_TIME_OUT_MILLS, TimeUnit.MILLISECONDS)
            .readTimeout(MAX_TIME_OUT_MILLS, TimeUnit.MILLISECONDS)
            .writeTimeout(MAX_TIME_OUT_MILLS, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    @Singleton
    @OcrRetrofit
    fun provideRetrofit(
        @OcrOkHttp okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_OCR_SERVER_URl)
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideOcrService(
        @OcrRetrofit retrofit: Retrofit
    ): OcrService = retrofit.create(OcrService::class.java)
}