package com.sample.noti.core.designsystem.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sample.noti.core.designsystem.theme.NotiTheme

data class ButtonSizeStyle(
    val paddingValues: PaddingValues,
    val radius: Dp = 0.dp,
    val textStyle: TextStyle,
    val iconSpacing: Dp = 0.dp,
    val iconSize: Dp = 24.dp,
)

val largeButtonStyle: ButtonSizeStyle
    @Composable get() = ButtonSizeStyle(
        paddingValues = PaddingValues(
            horizontal = NotiTheme.spacing.spacing5,
            vertical = 14.dp,
        ),
        radius = NotiTheme.radius.sm,
        textStyle = NotiTheme.typography.body1Medium,
        iconSpacing = NotiTheme.spacing.spacing2,
        iconSize = 24.dp,
    )

val mediumButtonStyle: ButtonSizeStyle
    @Composable get() = ButtonSizeStyle(
        paddingValues = PaddingValues(
            horizontal = NotiTheme.spacing.spacing4,
            vertical = NotiTheme.spacing.spacing3,
        ),
        radius = NotiTheme.radius.sm,
        textStyle = NotiTheme.typography.label1Medium,
        iconSpacing = NotiTheme.spacing.spacing1,
        iconSize = 22.dp,
    )

val smallButtonStyle: ButtonSizeStyle
    @Composable get() = ButtonSizeStyle(
        paddingValues = PaddingValues(
            horizontal = NotiTheme.spacing.spacing3,
            vertical = NotiTheme.spacing.spacing2,
        ),
        radius = NotiTheme.radius.xs,
        textStyle = NotiTheme.typography.label1Medium,
        iconSpacing = NotiTheme.spacing.spacing1,
        iconSize = 18.dp,
    )

val largeRoundedButtonStyle: ButtonSizeStyle
    @Composable get() = ButtonSizeStyle(
        paddingValues = PaddingValues(
            horizontal = NotiTheme.spacing.spacing5,
            vertical = NotiTheme.spacing.spacing3,
        ),
        radius = NotiTheme.radius.full,
        textStyle = NotiTheme.typography.body1Medium,
        iconSpacing = NotiTheme.spacing.spacing2,
        iconSize = 24.dp,
    )

val mediumRoundedButtonStyle: ButtonSizeStyle
    @Composable get() = ButtonSizeStyle(
        paddingValues = PaddingValues(
            horizontal = NotiTheme.spacing.spacing4,
            vertical = NotiTheme.spacing.spacing3,
        ),
        radius = NotiTheme.radius.full,
        textStyle = NotiTheme.typography.label1Medium,
        iconSpacing = NotiTheme.spacing.spacing1,
        iconSize = 22.dp,
    )

val smallRoundedButtonStyle: ButtonSizeStyle
    @Composable get() = ButtonSizeStyle(
        paddingValues = PaddingValues(
            horizontal = NotiTheme.spacing.spacing3,
            vertical = NotiTheme.spacing.spacing2,
        ),
        radius = NotiTheme.radius.full,
        textStyle = NotiTheme.typography.label1Medium,
        iconSpacing = NotiTheme.spacing.spacing1,
        iconSize = 18.dp,
    )
