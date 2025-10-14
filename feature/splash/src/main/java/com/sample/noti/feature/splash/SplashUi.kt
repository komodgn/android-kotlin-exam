package com.sample.noti.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.sample.noti.core.designsystem.DevicePreview
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.core.ui.component.NotiDialog
import com.sample.noti.feature.screens.SplashScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.android.components.ActivityRetainedComponent
import tech.thdev.compose.exteions.system.ui.controller.rememberSystemUiController

@CircuitInject(SplashScreen::class, ActivityRetainedComponent::class)
@Composable
fun SplashUi(
    modifier: Modifier = Modifier,
    state: SplashUiState,
) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false,
            isNavigationBarContrastEnforced = false,
        )

        onDispose {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = true,
                isNavigationBarContrastEnforced = false,
            )
        }
    }

    HandleSplashSideEffects(
        state = state,
        eventSink = state.eventSink,
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(NotiTheme.colors.basePrimary),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_app_logo),
                contentDescription = "Noti Logo",
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .height(IntrinsicSize.Max),
            )
            Spacer(
                modifier = Modifier.height(NotiTheme.spacing.spacing12),
            )
            Text(
                text = stringResource(R.string.splash_app_title),
                style = NotiTheme.typography.body1Bold,
                color = NotiTheme.colors.contentPrimary,
                textAlign = TextAlign.Center,
            )
            Spacer(
                modifier = Modifier.height(NotiTheme.spacing.spacing12),
            )
        }

        if (state.isForceUpdateDialogVisible) {
            NotiDialog(
                title = stringResource(R.string.splash_update_title),
                description = stringResource(R.string.splash_app_description),
                confirmButtonText = stringResource(R.string.splash_update_button_text),
                onConfirmRequest = {
                    state.eventSink(SplashUiEvent.OnUpdateButtonClick)
                },
            )
        }
    }
}

@DevicePreview
@Composable
private fun SplashPreview() {
    NotiTheme {
        SplashUi(
            state = SplashUiState(
                isForceUpdateDialogVisible = false,
                eventSink = {},
            ),
        )
    }
}