package com.sample.noti.feature.home

import com.sample.noti.feature.screens.component.MainTab
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

data class HomeUiState(
    val eventSink: (HomeUiEvent) -> Unit
) : CircuitUiState

sealed interface HomeUiEvent : CircuitUiEvent {
    data class OnTabSelected(val tab: MainTab) : HomeUiEvent
}
