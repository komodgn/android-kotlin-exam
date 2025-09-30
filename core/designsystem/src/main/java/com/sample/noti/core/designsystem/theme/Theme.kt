package com.sample.noti.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalColorScheme = staticCompositionLocalOf { NotiColorScheme() }
private val LocalTypography = staticCompositionLocalOf { NotiTypography() }
private val LocalSpacing = staticCompositionLocalOf { NotiSpacing() }
private val LocalRadius = staticCompositionLocalOf { NotiRadius() }
private val LocalBorder = staticCompositionLocalOf { NotiBorder() }

@Composable
fun NotiTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        content = content,
    )
}

object NotiTheme {
    val colors: NotiColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: NotiTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val spacing: NotiSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

    val radius: NotiRadius
        @Composable
        @ReadOnlyComposable
        get() = LocalRadius.current

    val border: NotiBorder
        @Composable
        @ReadOnlyComposable
        get() = LocalBorder.current
}
