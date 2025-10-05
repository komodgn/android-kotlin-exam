package com.sample.noti.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sample.noti.core.designsystem.DevicePreview
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.feature.screens.HomeScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.android.components.ActivityRetainedComponent

@Composable
@CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
fun HomeUi(state: HomeUiState, modifier: Modifier = Modifier) {

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        color = NotiTheme.colors.contentPrimary
                    )
                }

                state.errorText != null -> {
                    Text(
                        text = "에러: ${state.errorText}",
                        color = NotiTheme.colors.contentWarning,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                state.title != null -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = state.title,
                                        style = NotiTheme.typography.body1Bold,
                                        fontWeight = FontWeight.Bold,
                                        color = NotiTheme.colors.contentBrand,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )

                                    Text(
                                        text = state.body ?: "",
                                        style = NotiTheme.typography.body1Bold,
                                        color = NotiTheme.colors.contentPrimary
                                    )
                                }
                            }
                        }
                    }
                }

                else -> {
                    Text(
                        "임시로 생성한 홈 화면 (데이터 대기 중)",
                        color = NotiTheme.colors.contentBrand
                    )
                }
            }
        }
    }
}

@DevicePreview
@Composable
fun HomeUiPreview() {
    NotiTheme {
        HomeUi(state = HomeUiState(), modifier = Modifier)
    }
}