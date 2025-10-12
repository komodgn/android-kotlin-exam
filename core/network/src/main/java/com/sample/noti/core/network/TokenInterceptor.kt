package com.sample.noti.core.network

import com.sample.noti.core.datastore.api.datasource.TokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class TokenInterceptor @Inject constructor(
    private val tokenDataSource: TokenDataSource
) : Interceptor {

    private val publicEndpoints = setOf(
        "api/v1/auth/login",
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url.toString()

        val isPublicEndpoint = publicEndpoints.any { requestUrl.contains(it) }

        return if (isPublicEndpoint) {
            chain.proceed(request)
        } else {
            val accessToken = runBlocking { tokenDataSource.getAccessToken() }
            chain.proceed(
                request.newBuilder()
                    .addHeader("Authorization", "Bearer $accessToken")
                    .build()
            )
        }
    }
}