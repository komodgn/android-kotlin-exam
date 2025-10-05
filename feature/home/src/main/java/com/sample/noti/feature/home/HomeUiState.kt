package com.sample.noti.feature.home

import com.slack.circuit.runtime.CircuitUiState

data class HomeUiState(
    val isLoading: Boolean = false,
    val title: String? = null,
    val body: String? = null,
    val errorText: String? = null
) : CircuitUiState
