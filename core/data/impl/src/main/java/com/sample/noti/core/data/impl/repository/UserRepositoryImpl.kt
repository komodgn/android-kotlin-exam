package com.sample.noti.core.data.impl.repository

import com.sample.noti.core.common.utils.runSuspendCatching
import com.sample.noti.core.data.api.repository.UserRepository
import com.sample.noti.core.data.impl.mapper.toModel
import com.sample.noti.core.datastore.api.datasource.OnboardingDataSource
import com.sample.noti.core.model.OnboardingState
import com.sample.noti.core.model.TermsAgreementModel
import com.sample.noti.core.model.UserProfileModel
import com.sample.noti.core.network.service.NotiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val providerService: NotiService,
    private val onboardingDataSource: OnboardingDataSource
) : UserRepository {

    override val onboardingState: Flow<OnboardingState> = onboardingDataSource.onboardingState

    override suspend fun setOnboardingCompleted(isCompleted: Boolean) {
        onboardingDataSource.setOnboardingCompleted(isCompleted)
    }

    override suspend fun getUserProfile(): Result<UserProfileModel> = runSuspendCatching {
        providerService.getUserProfile().toModel()
    }
}
