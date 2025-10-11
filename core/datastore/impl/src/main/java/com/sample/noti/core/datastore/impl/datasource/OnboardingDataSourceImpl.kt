package com.sample.noti.core.datastore.impl.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.sample.noti.core.datastore.api.datasource.OnboardingDataSource
import com.sample.noti.core.datastore.impl.di.OnboardingDataStore
import com.sample.noti.core.model.OnboardingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnboardingDataSourceImpl @Inject constructor(
    @OnboardingDataStore private val dataStore: DataStore<Preferences>
): OnboardingDataSource {

    override val onboardingState: Flow<OnboardingState> = dataStore.data
        .map { prefs ->
            when (prefs[IS_ONBOARDING_COMPLETED] ?: false) {
                false -> OnboardingState.NOT_COMPLETED
                true -> OnboardingState.COMPLETED
            }
        }

    override suspend fun setOnboardingCompleted(isCompleted: Boolean) {
        dataStore.edit { prefs ->
            prefs[IS_ONBOARDING_COMPLETED] = isCompleted
        }
    }

    private companion object {
        val IS_ONBOARDING_COMPLETED = booleanPreferencesKey("IS_ONBOARDING_COMPLETED")
    }
}