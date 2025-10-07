package com.sample.noti.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sample.noti.core.designsystem.DevicePreview
import com.sample.noti.core.designsystem.theme.MainBg
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.core.ui.NotiScaffold
import com.sample.noti.feature.home.component.HomeHeader
import com.sample.noti.feature.screens.HomeScreen
import com.sample.noti.feature.screens.component.MainBottomBar
import com.sample.noti.feature.screens.component.MainTab
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.collections.immutable.toImmutableList

@Composable
@CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
fun HomeUi(
    state: HomeUiState,
    modifier: Modifier = Modifier
) {
    NotiScaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            MainBottomBar(
                tabs = MainTab.entries.toImmutableList(),
                currentTab = MainTab.HOME,
                onTabSelected = { tab ->
                    state.eventSink(HomeUiEvent.OnTabSelected(tab))
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBg)
                .padding(innerPadding)
        ) {
            HomeHeader()
            HomeContent(
                state = state
            )
        }
    }
}

@Composable
private fun HomeContent(
    state: HomeUiState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NotiTheme.colors.baseSecondary)
    ) {
        // TODO: 상태 별 화면 출력
        // if GESTMODE
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "홈 화면",
                color = NotiTheme.colors.contentPrimary
            )
        }
        // else
            // when UI State - IDLE, LOADING, SUCCESS, ERROR
    }
}

@DevicePreview
@Composable
fun HomeUiPreview() {
    NotiTheme {
        HomeUi(
            state = HomeUiState(
                eventSink = {},
            ),
            modifier = Modifier
        )
    }
}