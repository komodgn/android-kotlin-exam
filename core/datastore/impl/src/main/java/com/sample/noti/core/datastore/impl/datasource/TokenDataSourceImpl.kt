package com.sample.noti.core.datastore.impl.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sample.noti.core.datastore.api.datasource.TokenDataSource
import com.sample.noti.core.datastore.impl.di.TokenDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenDataSourceImpl @Inject constructor(
    @TokenDataStore private val dataStore: DataStore<Preferences>,
) : TokenDataSource {
    override val accessToken: Flow<String> = dataStore.data
        .map { prefs ->
            prefs[ACCESS_TOKEN] ?: DEFAULT_TOKEN
        }

    override val refreshToken: Flow<String> = dataStore.data
        .map { prefs ->
            prefs[REFRESH_TOKEN] ?: DEFAULT_TOKEN
        }

    override suspend fun getAccessToken(): String {
        return accessToken.first()
    }

    override suspend fun getRefreshToken(): String {
        return refreshToken.first()
    }

    override suspend fun setAccessToken(accessToken: String) {
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = accessToken
        }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        dataStore.edit { prefs ->
            prefs[REFRESH_TOKEN] = refreshToken
        }
    }

    override suspend fun clearTokens() {
        dataStore.edit { prefs ->
            prefs.remove(ACCESS_TOKEN)
            prefs.remove(REFRESH_TOKEN)
        }
    }

    private companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        const val DEFAULT_TOKEN = ""
    }
}