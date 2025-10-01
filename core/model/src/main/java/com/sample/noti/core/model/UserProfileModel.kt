package com.sample.noti.core.model

import androidx.compose.runtime.Immutable

@Immutable
data class UserProfileModel (
    val id: String,
    val email: String,
    val nickname: String,
    val provider: String,
    val termsAgreed: Boolean,
)