package com.sample.noti.core.network

import com.orhanobut.logger.Logger
import com.sample.noti.core.datastore.api.datasource.TokenDataSource
import com.sample.noti.core.network.request.RefreshTokenRequest
import com.sample.noti.core.network.service.NotiService
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider

@Suppress("TooGenericExceptionCaught")
class TokenAuthenticator @Inject constructor(
    private val tokenDataSource: TokenDataSource,
    private val serviceProvider: Provider<NotiService>,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            try {
                val refreshToken = tokenDataSource.getRefreshToken()

                if (refreshToken.isBlank()) {
                    Logger.d("TokenAuthenticator", "No refresh token available")
                    tokenDataSource.clearTokens()
                    return@runBlocking null
                }

                val refreshTokenRequest = RefreshTokenRequest(refreshToken)
                val refreshResponse = serviceProvider.get().refreshToken(refreshTokenRequest)

                tokenDataSource.apply {
                    setAccessToken(refreshResponse.accessToken)
                    setRefreshToken(refreshResponse.refreshToken)
                }

                Logger.d("TokenAuthenticator", "Token refreshed successfully")

                response.request.newBuilder()
                    .header("Authorization", "Bearer ${refreshResponse.accessToken}")
                    .build()
            } catch (e: Exception) {
                Logger.e("TokenAuthenticator", e.message)
                tokenDataSource.clearTokens()
                
                return@runBlocking null
            }
        }
    }
}
