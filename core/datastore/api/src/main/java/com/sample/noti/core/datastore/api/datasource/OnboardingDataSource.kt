package com.sample.noti.core.datastore.api.datasource

import com.sample.noti.core.model.OnboardingState
import kotlinx.coroutines.flow.Flow

interface OnboardingDataSource {
    val onboardingState: Flow<OnboardingState>
    suspend fun setOnboardingCompleted(isCompleted: Boolean)
}