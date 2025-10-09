package com.sample.noti.feature.onboarding

import androidx.compose.foundation.pager.PagerState
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

data class OnboardingUiState(
    val pagerState: PagerState,
    val eventSink: (OnboardingUiEvent) -> Unit
) : CircuitUiState

sealed interface OnboardingUiEvent : CircuitUiEvent {
    data class OnNextButtonClick(val currentPage: Int): OnboardingUiEvent
    data class OnPreviousButtonClick(val currentPage: Int): OnboardingUiEvent
    data object OnSkipButtonClick: OnboardingUiEvent
}
