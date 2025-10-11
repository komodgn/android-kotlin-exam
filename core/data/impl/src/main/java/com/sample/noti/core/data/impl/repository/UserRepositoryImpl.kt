package com.sample.noti.core.data.impl.repository

import com.sample.noti.core.data.api.repository.UserRepository
import com.sample.noti.core.datastore.api.datasource.OnboardingDataSource
import com.sample.noti.core.model.OnboardingState
import com.sample.noti.core.model.TermsAgreementModel
import com.sample.noti.core.model.UserProfileModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val onboardingDataSource: OnboardingDataSource
) : UserRepository {

    override val onboardingState: Flow<OnboardingState> = onboardingDataSource.onboardingState

    override suspend fun setOnboardingCompleted(isCompleted: Boolean) {
        onboardingDataSource.setOnboardingCompleted(isCompleted)
    }
}
