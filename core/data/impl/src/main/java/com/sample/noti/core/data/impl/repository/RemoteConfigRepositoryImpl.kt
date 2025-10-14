package com.sample.noti.core.data.impl.repository

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.get
import com.orhanobut.logger.Logger
import com.sample.noti.core.common.utils.isUpdateRequired
import com.sample.noti.core.data.api.repository.RemoteConfigRepository
import com.sample.noti.core.data.impl.BuildConfig
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class RemoteConfigRepositoryImpl @Inject constructor(
    private val  remoteConfig: FirebaseRemoteConfig
) : RemoteConfigRepository {
    override suspend fun getLatestVersion(): Result<String> = suspendCancellableCoroutine { continuation ->
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val latestVersion = remoteConfig.getString(KEY_LATEST_VERSION)
                Logger.d("latestVersion: $latestVersion")
                continuation.resume(Result.success(latestVersion))
            } else {
                Logger.e(task.exception, "RemoteConfig getLatestVersion failed")
                continuation.resume(Result.failure(task.exception ?: Exception("Unknown error")))
            }
        }
    }

    override suspend fun shouldUpdate(): Result<Boolean> = suspendCancellableCoroutine { continuation ->
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val minVersion = remoteConfig.getString(KEY_MIN_VERSION)
                val currentVersion = BuildConfig.APP_VERSION
                
                Logger.d("currentVersion: $currentVersion, minVersion: $minVersion")
                continuation.resume(Result.success(isUpdateRequired(currentVersion, minVersion)))
            } else {
                Logger.e(task.exception, "RemoteConfig shouldUpdate failed")
                continuation.resume(Result.failure(task.exception ?: Exception("Unknown error")))
            }
        }
    }

    private companion object {
        const val KEY_LATEST_VERSION = "LatestVersion"
        const val KEY_MIN_VERSION = "MinVersion"
    }
}