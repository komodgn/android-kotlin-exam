package com.sample.noti.core.data.api.repository

import com.sample.noti.core.model.OnboardingState
import com.sample.noti.core.model.TermsAgreementModel
import com.sample.noti.core.model.UserProfileModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val onboardingState: Flow<OnboardingState>
    suspend fun setOnboardingCompleted(isCompleted: Boolean)
    suspend fun getUserProfile(): Result<UserProfileModel>
    suspend fun agreeTrems(termsAgreed: Boolean): Result<TermsAgreementModel>
}