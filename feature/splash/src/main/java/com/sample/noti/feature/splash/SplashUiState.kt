package com.sample.noti.feature.splash

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

/**
 * State는 UI가 표시해야 하는 모든 정보를 담고 있는 Immutable 데이터입니다.
 * 화면에 표시할 데이터(val count: Int 등)와
 * UI에서 Presenter로 액션을 전달하기 위한 Event Sink(val eventSink: (Event) -> Unit)로 구성됩니다.
 */
data class SplashUiState(
    val isForceUpdateDialogVisible: Boolean = false,
    val eventSink: (SplashUiEvent) -> Unit,
    val sideEffect: SplashSideEffect? = null
) : CircuitUiState

sealed interface SplashSideEffect {
    data object NavigateToPlayStore: SplashSideEffect
//    data object NavigateToMain: SplashSideEffect
}

sealed interface SplashUiEvent: CircuitUiEvent {
    data object InitSideEffect: SplashUiEvent
    data object OnUpdateButtonClick: SplashUiEvent
}