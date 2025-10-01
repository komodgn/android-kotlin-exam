package com.sample.noti.core.common.utils

import com.orhanobut.logger.Logger

/**
 * 버전 문자열 "X.Y.Z"를 정수 배열[X, Y, Z]로 변환합니다.
 *
 * @param versionString "X.Y.Z" 형태의 버전 문자열
 * @return 파싱된 정수 배열
 */
private fun parseVersion(versionString: String): IntArray {
    if (versionString.isBlank()) {
        Logger.e("Version string is blank")
        return intArrayOf()
    }

    val parts = versionString.split(".").mapNotNull { it.toIntOrNull() }

    // X.Y.Z 형태를 기대 but 최소한 Major와 Minor는 있어야 유효하다고 간주 (길이 2 이상)
    if (parts.size < 2) {
        Logger.e("Version string format error: $versionString. Expected format: X.Y.Z")
        return intArrayOf()
    }

    return parts.toIntArray()
}

/**
 * 파싱된 두 버전 배열을 비교합니다.
 *
 * @param currentParts 현재 버전의 정수 배열
 * @param minParts 최소 버전의 정수 배열
 * @return 현재 버전이 최소 버전보다 크거나 같으면 true, 그렇지 않으면 false
 */
private fun compareVersionParts(currentParts: IntArray, minParts: IntArray): Boolean {
    val maxLength = maxOf(currentParts.size, minParts.size)

    for (i in 0 until maxLength) {
        // 해당 세그먼트가 없으면 0으로 간주하여 비교
        val currentSegment = currentParts.getOrElse(i) { 0 }
        val minSegment = minParts.getOrElse(i) {0}

        when {
            currentSegment < minSegment -> {
                // 현재 세그먼트가 최소 세그먼트보다 작으면 업데이트 필요
                Logger.d("Update required: Current Segment $currentSegment < Min Segment $minSegment")
                return true
            }
            currentSegment > minSegment -> {
                // 현재 세그먼트가 최소 세그먼트보다 크다면 업데이트 불필요
                Logger.d("Update not required: Current Segment $currentSegment > Min Segment $minSegment")
                return false
            }
            // 세그먼트가 같다면 다음 세그먼트로 넘어갑니다.
        }
    }

    // 모든 세그먼트가 같거나 currentVersion이 더 크면 업데이트 불필요
    return false
}

/**
 * 현재 설치된 앱 버전이 업데이트가 필요한 최소 버전보다 낮은지 확인하여 업데이트 필요 여부를 판단합니다.
 *
 * @param currentVersion 현재 기기에 설치된 앱 버전 (ex. "1.0.0")
 * @param minVersion 업데이트가 필요한 최소 버전 (ex. "1.0.0")
 * @return 업데이트가 필요한 경우 true, 그렇지 않은 경우 false
 */
fun isUpdateRequired(currentVersion: String, minVersion: String): Boolean {
    val currentParts = parseVersion(currentVersion)
    val minParts = parseVersion(minVersion)

    if (currentVersion.isEmpty() || minVersion.isEmpty()) {
        Logger.e("Version string is empty")
        return false
    }

    return compareVersionParts(currentParts, minParts)
}