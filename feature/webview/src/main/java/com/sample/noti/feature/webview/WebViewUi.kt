package com.sample.noti.feature.webview

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.sample.noti.core.designsystem.DevicePreview
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.core.ui.NotiScaffold
import com.sample.noti.feature.screens.WebViewScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.android.components.ActivityRetainedComponent

@CircuitInject(WebViewScreen::class, ActivityRetainedComponent::class)
@Composable
internal fun WebViewUi(
    state: WebViewUiState,
    modifier: Modifier = Modifier
) {
    NotiScaffold(
        modifier = modifier
            .fillMaxSize()
    ) { innerpadding ->
        WebViewContent(
            state = state,
            innerPadding = innerpadding
        )
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
internal fun WebViewContent(
    state: WebViewUiState,
    modifier: Modifier = Modifier,
    innerPadding : PaddingValues,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        // TODO: 뒤로 가기 버튼
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )

                    webViewClient = WebViewClient()
                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        // 반응형 레이아웃 조정(뷰포트 메타태그 지원)
                        useWideViewPort = true
                        // 화면에 맞게 조정(모바일 최적화)
                        loadWithOverviewMode = true
                    }
                    loadUrl(state.url)
                }
            },
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@DevicePreview
@Composable
private fun WebViewUiPreview() {
    NotiTheme {
        WebViewUi(
            state = WebViewUiState(
                url = "https://github.com/komodgn",
                title = "웹뷰 미리보기",
                eventSink = {}
            ),
        )
    }
}