package com.sample.noti.core.data.api.repository

interface RemoteConfigRepository {
    /**
     * 서버에서 설정한 최신 앱 버전을 비동기로 가져옵니다.
     * 강제 업데이트가 아닌, 사용자에게 단순 알림을 줄 때 사용합니다.
     */
    suspend fun getLatestVersion(): Result<String>

    /**
     * 현재 앱 버전과 서버에서 설정한 최소 허용 버전을 비교하여
     * 앱 업데이트가 필수적인지 여부를 비동기로 확인합니다.
     */
    suspend fun shouldUpdate(): Result<Boolean>
}