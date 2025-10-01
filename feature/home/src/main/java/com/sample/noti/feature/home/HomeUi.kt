package com.sample.noti.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.core.designsystem.theme.Orange50
import com.sample.noti.feature.screens.HomeScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

// TODO: state, presenter 분리
class HomePresenter @AssistedInject constructor() : Presenter<HomeUiState> {
    @Composable
    override fun present(): HomeUiState {
        return HomeUiState
    }

    @AssistedFactory
    @CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
    interface Factory {
        fun create(
            // navigator: Navigator,
            // screen: HomeScreen
        ): HomePresenter
    }
}

object HomeUiState: CircuitUiState

@Composable
@CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
fun HomeUi(state: HomeUiState, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Orange50),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "임시로 생성한 홈 화면",
            color = NotiTheme.colors.contentBrand
        )
    }
}
