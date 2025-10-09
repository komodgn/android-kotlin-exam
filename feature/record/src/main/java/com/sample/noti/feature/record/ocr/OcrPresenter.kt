package com.sample.noti.feature.record.ocr

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.sample.noti.core.ocr.recognizer.OcrRecognizer
import com.sample.noti.feature.screens.OcrScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.persistentListOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import com.slack.circuit.retained.rememberRetained
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch

class OcrPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val recognizer: OcrRecognizer
    // TODO: Add Analytics Helper
) : Presenter<OcrUiState> {

    @Composable
    override fun present(): OcrUiState {
        val scope = rememberCoroutineScope()
        var currentUi by rememberRetained { mutableStateOf(OcrUi.CAMERA) }
        var isPermissionDialogVisible by rememberRetained { mutableStateOf(false) }
        var textList by rememberRetained { mutableStateOf(persistentListOf<String>()) }
        var recognizedText by rememberRetained { mutableStateOf("") }

        fun recognizeText(imageUri: Uri) {
            scope.launch {
                Log.d("OcrPresenter", "API 호출 시작: $imageUri")
                try {
                    recognizer.recognizeText(imageUri)
                        .onSuccess {
                            val responseText = it.responses.firstOrNull()?.fullTextAnnotation?.text.orEmpty()
                            recognizedText = responseText
                            Log.d("OcrPresenter", "API 호출 성공, 인식된 텍스트: $responseText")

                            if (responseText.isNotBlank()) {
                                val sentences = responseText
                                    .split("\n")
                                    .map { it.trim() }
                                    .filter { it.isNotEmpty() }

                                textList = sentences.toPersistentList()
                                currentUi = OcrUi.RESULT
                            }
                        }
                        .onFailure {
                            // TODO: Error Handle
                            Log.e("OcrPresenter", "API 호출 실패", it)
                        }
                } finally {
                    // TODO: Change Loading Status
                }
            }
        }

        fun handleEvent(event: OcrUiEvent) {
            when (event) {
                is OcrUiEvent.OnShowPermissionDialog -> {
                    isPermissionDialogVisible = true
                }

                is OcrUiEvent.OnImageCaptured -> {
                    recognizeText(event.imageUri)
                }
            }
        }

        return OcrUiState(
            currentUi = currentUi,
            isPermissionDialogVisible = isPermissionDialogVisible,
            textList = textList,
            eventSink = ::handleEvent
        )
    }

    @CircuitInject(OcrScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): OcrPresenter
    }
}