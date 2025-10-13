package com.sample.noti.core.network.service

import com.sample.noti.core.network.request.LoginRequest
import com.sample.noti.core.network.request.RefreshTokenRequest
import com.sample.noti.core.network.response.LoginResponse
import com.sample.noti.core.network.response.RefreshTokenResponse
import com.sample.noti.core.network.response.UserProfileResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface NotiService {
    /**
     * Auth - public
     */
    @POST("/api/v1/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("/api/v1/auth/refresh")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): RefreshTokenResponse

    /**
     * Auth (private)
     */
    @POST("api/v1/auth/logout")
    suspend fun logout()

    @DELETE("api/v1/auth/withdraw")
    suspend fun withdraw()

    /**
     * User (private)
     */
    @GET("/api/v1/users/me")
    suspend fun getUserProfile(): UserProfileResponse

}