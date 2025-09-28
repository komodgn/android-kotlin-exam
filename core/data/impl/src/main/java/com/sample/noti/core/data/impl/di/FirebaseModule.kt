package com.sample.noti.core.data.impl.di

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.apply
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.sample.noti.core.data.impl.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
internal object FirebaseModule {
    /**
     * Firebase Remote Config 인스턴스를 제공하고 초기 설정을 적용합니다.
     * DEBUG 모드에서는 즉시 설정을 가져올 수 있도록 최소 페치 간격을 0초로 설정합니다.
     */
    @Singleton
    @Provides
    fun provideRemoteConfig(): FirebaseRemoteConfig {
        return Firebase.remoteConfig.apply {
            // Lazy로 설정 객체를 생성하여 필요한 경우에만 초기화
            val configSettings by lazy {
                remoteConfigSettings {
                    // 디버그 시에는 테스트를 위해 즉시 페치, 릴리즈 시에는 60초 간격으로 제한
                    minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0 else 60
                }
            }
            // 비동기로 설정 적용
            setConfigSettingsAsync(configSettings)
        }
    }
}