package com.sample.noti.core.data.api.repository

import com.sample.noti.core.model.AutoLoginState
import com.sample.noti.core.model.UserState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(accessToken: String): Result<Unit>
    suspend fun logout(): Result<Unit>
    suspend fun withdraw(): Result<Unit>
    suspend fun getCurrentUserState(): UserState
    val userState: Flow<UserState>
    val autoLoginState: Flow<AutoLoginState>
}