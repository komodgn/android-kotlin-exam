package com.sample.noti.feature.record.ocr

import android.net.Uri
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

enum class OcrUi {
    CAMERA,
    RESULT
}

data class OcrUiState(
    val currentUi: OcrUi = OcrUi.CAMERA,
    val isPermissionDialogVisible: Boolean = false,
    val textList: ImmutableList<String> = persistentListOf(),
    val eventSink: (OcrUiEvent) -> Unit
): CircuitUiState

sealed interface OcrUiEvent : CircuitUiEvent {
    data object OnShowPermissionDialog : OcrUiEvent
    data class OnImageCaptured(val imageUri: Uri): OcrUiEvent
}