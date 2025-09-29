package com.sample.noti.core.common.util

import com.google.common.truth.Truth.assertThat
import com.sample.noti.core.common.utils.isUpdateRequired
import org.junit.Test

/**
 * isUpdatedRequired 함수를 테스트하는 클래스입니다.
 * 버전 문자열 비교 로직의 정확성을 검증합니다.
 */
class VersionUtilTest {

    @Test
    fun testUpdateRequired_WhenCurrentIsLowerThanMin_ReturnsTrue() {
        assertThat(isUpdateRequired("1.0.0", "1.0.1")).isTrue()
        assertThat(isUpdateRequired("1.5.0", "2.0.0")).isTrue()
        assertThat(isUpdateRequired("1.0.5", "1.1.0")).isTrue()
    }

    @Test
    fun testUpdateNotRequired_WhenCurrentIsEqualToOrHigherThanMin_ReturnsFalse() {
        assertThat(isUpdateRequired("1.0.0", "1.0.0")).isFalse()
        assertThat(isUpdateRequired("1.0.1", "1.0.0")).isFalse()
        assertThat(isUpdateRequired("1.1.0", "1.0.5")).isFalse()
        assertThat(isUpdateRequired("3.0.0", "1.5.5")).isFalse()
    }

    @Test
    fun testUpdateRequired_WhenMinHasMoreSegments_ReturnsTrue() {
        assertThat(isUpdateRequired("1.0", "1.0.1")).isTrue()
        assertThat(isUpdateRequired("1.0.0", "1.0.0.1")).isTrue()
    }

    @Test
    fun testUpdateNotRequired_WhenCurrentHasSegments_ReturnsFalse() {
        assertThat(isUpdateRequired("1.0.1", "1.0")).isFalse()
        assertThat(isUpdateRequired("1.0.0.1", "1.0.0")).isFalse()
    }
}