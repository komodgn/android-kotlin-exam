package com.sample.noti.core.network.di

//import com.sample.noti.core.network.TokenInterceptor
//import com.sample.noti.core.network.TokenAuthenticator
import com.sample.noti.core.network.BuildConfig
import com.sample.noti.core.network.service.CatFactsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val MaxTimeoutMillis = 10_000L

private val jsonRule = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    prettyPrint = true
}

private val jsonConverterFactory = jsonRule.asConverterFactory("application/json".toMediaType())

private val FILTERED_HEADERS = setOf(
    "transfer-encoding",
    "connection",
    "x-content-type-options",
    "x-xss-protection",
    "cache-control",
    "pragma",
    "expires",
    "x-frame-options",
    "keep-alive",
    "server",
    "content-length",
)

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Singleton
    @Provides
    internal fun provideOkHttpClient(
        // TODO: Add Logging, TokenInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
            .readTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
            .writeTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
            // TODO: Add Logging, TokenInterceptor
            .build()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    internal fun provideCatFactsService(
        retrofit: Retrofit,
    ): CatFactsService {
        return retrofit.create()
    }
}