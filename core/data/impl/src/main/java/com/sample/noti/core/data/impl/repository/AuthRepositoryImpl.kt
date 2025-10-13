package com.sample.noti.core.data.impl.repository

import com.sample.noti.core.common.utils.runSuspendCatching
import com.sample.noti.core.data.api.repository.AuthRepository
import com.sample.noti.core.datastore.api.datasource.TokenDataSource
import com.sample.noti.core.model.AutoLoginState
import com.sample.noti.core.model.UserState
import com.sample.noti.core.network.request.LoginRequest
import com.sample.noti.core.network.service.NotiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val providerService: NotiService,
    private val tokenDataSource: TokenDataSource,
) : AuthRepository {
    override val userState: Flow<UserState> = tokenDataSource.accessToken
        .map { accessToken ->
            if (accessToken.isBlank()) UserState.Guest else UserState.LoggedIn
        }

    override val autoLoginState: Flow<AutoLoginState> = tokenDataSource.accessToken
        .map { accessToken ->
            if (accessToken.isBlank()) AutoLoginState.NOT_LOGGED_IN else AutoLoginState.LOGGED_IN
        }

    override suspend fun getCurrentUserState(): UserState {
        val accessToken = tokenDataSource.getAccessToken()
        return if (accessToken.isBlank()) UserState.Guest else UserState.LoggedIn
    }

    override suspend fun login(accessToken: String) = runSuspendCatching {
        val loginResponse = providerService.login(
            loginRequest = LoginRequest(
                providerType = "KAKAO",
                accessToken = accessToken
            )
        )
        saveTokens(loginResponse.accessToken, loginResponse.refreshToken)
    }

    override suspend fun logout() = runSuspendCatching {
        providerService.logout()
        clearTokens()
    }

    override suspend fun withdraw() = runSuspendCatching {
        providerService.withdraw()
        clearTokens()
    }

    private suspend fun saveTokens(accessToken: String, refreshToken: String) {
        tokenDataSource.apply {
            setAccessToken(accessToken)
            setRefreshToken(refreshToken)
        }
    }

    private suspend fun clearTokens() {
        tokenDataSource.clearTokens()
    }
}
