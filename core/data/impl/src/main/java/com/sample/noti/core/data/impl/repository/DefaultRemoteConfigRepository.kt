package com.sample.noti.core.data.impl.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.get
import com.orhanobut.logger.Logger
import com.sample.noti.core.data.api.repository.RemoteConfigRepository
import com.sample.noti.core.data.impl.BuildConfig
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultRemoteConfigRepository @Inject constructor(
    private val  remoteConfig: FirebaseRemoteConfig
) : RemoteConfigRepository {

    companion object {
        private const val KEY_LATEST_VERSION = "LatestVersion"
        private const val KEY_MIN_VERSION = "MinVersion"
    }

    override suspend fun getLatestVersion(): Result<String> = suspendCancellableCoroutine { continuation ->
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val latestVersion = remoteConfig[KEY_LATEST_VERSION].asString()
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
                val minVersion = remoteConfig[KEY_MIN_VERSION].asString()
                val currentVersion = BuildConfig.APP_VERSION
                Logger.d("currentVersion: $currentVersion, minVersion: $minVersion")
                // TODO: 버전 비교 유틸 메서드ㅡ 구현
                continuation.resume(Result.success(isUpdateRequired(currentVersion, minVersion)))
            } else {
                Logger.e(task.exception, "RemoteConfig shouldUpdate failed")
                continuation.resume(Result.failure(task.exception ?: Exception("Unknown error")))

            }
        }

    }
}