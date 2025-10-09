package com.sample.noti.feature.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sample.noti.core.designsystem.DevicePreview
import com.sample.noti.core.designsystem.component.button.NotiButtonColorStyle
import com.sample.noti.core.designsystem.component.button.NotiTextButton
import com.sample.noti.core.designsystem.component.button.largeButtonStyle
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.core.designsystem.theme.Orange50
import com.sample.noti.core.ui.NotiScaffold
import com.sample.noti.feature.onboarding.component.OnboardingPage
import com.sample.noti.feature.onboarding.component.PageIndicator
import com.sample.noti.feature.screens.OnboardingScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.android.components.ActivityRetainedComponent

@Composable
@CircuitInject(OnboardingScreen::class, ActivityRetainedComponent::class)
fun OnboardingUi(
    state: OnboardingUiState,
    modifier: Modifier = Modifier
) {
    NotiScaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = Orange50
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(

            ) {
                NotiTextButton(
                    text = stringResource(R.string.previous),
                    onClick = { state.eventSink(OnboardingUiEvent.OnPreviousButtonClick(state.pagerState.currentPage)) },
                    colorStyle = NotiButtonColorStyle.TEXT,
                    sizeStyle = largeButtonStyle,
                )
                NotiTextButton(
                    text = stringResource(R.string.skip),
                    onClick = { state.eventSink(OnboardingUiEvent.OnSkipButtonClick) },
                    colorStyle = NotiButtonColorStyle.TEXT,
                    sizeStyle = largeButtonStyle,
                )
            }

            HorizontalPager(
                state = state.pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                when (page) {
                    0 -> {
                        OnboardingPage(
                            titleRes = R.string.onboarding_first_page_title,
                            imageRes = R.drawable.img_onboarding_first,
                            descRes = R.string.onboarding_first_page_description
                        )
                    }
                    1 -> {
                        OnboardingPage(
                            titleRes = R.string.onboarding_second_page_title,
                            imageRes = R.drawable.img_onboarding_second,
                            descRes = R.string.onboarding_second_page_description
                        )
                    }
                    2 -> {
                        OnboardingPage(
                            titleRes = R.string.onboarding_third_page_title,
                            imageRes = R.drawable.img_onboarding_third,
                            descRes = R.string.onboarding_third_page_description
                        )
                    }
                }
            }
            PageIndicator(
                pageCount = ONBOARDING_PAGE_COUNT,
                pagerState = state.pagerState
            )
            NotiTextButton(
                text = stringResource(R.string.next),
                onClick = { state.eventSink(OnboardingUiEvent.OnNextButtonClick(state.pagerState.currentPage)) },
                colorStyle = NotiButtonColorStyle.TEXT,
                sizeStyle = largeButtonStyle,
            )
        }
    }
}

@DevicePreview
@Composable
private fun OnboardingUiPreview() {
    NotiTheme {
        OnboardingUi(
            state = OnboardingUiState(
                pagerState = rememberPagerState(pageCount = { ONBOARDING_PAGE_COUNT }),
                eventSink = {}
            )
        )
    }
}
