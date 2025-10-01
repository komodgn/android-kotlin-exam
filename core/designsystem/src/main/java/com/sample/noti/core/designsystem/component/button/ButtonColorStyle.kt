package com.sample.noti.core.designsystem.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.core.designsystem.theme.Kakao
import com.sample.noti.core.designsystem.theme.Orange400

enum class NotiButtonColorStyle {
    PRIMARY, SECONDARY, TERTIARY, STROKE, TEXT, KAKAO;

    @Composable
    fun containerColor(isPressed: Boolean = false) = when (this) {
        PRIMARY -> if (isPressed) NotiTheme.colors.bgPrimaryPressed else NotiTheme.colors.bgPrimary
        SECONDARY -> if (isPressed) NotiTheme.colors.bgSecondaryPressed else NotiTheme.colors.bgSecondary
        TERTIARY -> if (isPressed) NotiTheme.colors.bgTertiaryPressed else NotiTheme.colors.bgTertiary
        STROKE -> if (isPressed) NotiTheme.colors.basePrimary else NotiTheme.colors.basePrimary
        TEXT -> Color.Transparent
        KAKAO -> if (isPressed) Orange400 else Kakao
    }

    @Composable
    fun contentColor() = when (this) {
        PRIMARY -> NotiTheme.colors.contentInverse
        SECONDARY -> NotiTheme.colors.contentPrimary
        TERTIARY -> NotiTheme.colors.contentBrand
        STROKE -> NotiTheme.colors.contentBrand
        TEXT -> NotiTheme.colors.contentSecondary
        KAKAO -> NotiTheme.colors.contentPrimary
    }

    @Composable
    fun disabledContainerColor() = when (this) {
        TEXT -> Color.Transparent
        else -> NotiTheme.colors.bgDisabled
    }

    @Composable
    fun disabledContentColor() = NotiTheme.colors.contentDisabled

    @Composable
    fun borderStroke() = when (this) {
        STROKE -> BorderStroke(1.dp, NotiTheme.colors.borderPrimary)
        else -> null
    }
}