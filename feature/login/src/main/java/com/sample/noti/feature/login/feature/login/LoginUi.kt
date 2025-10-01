package com.sample.noti.feature.login.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.core.designsystem.theme.Orange50
import com.sample.noti.feature.screens.LoginScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

// TODO: state, presenter 분리
class LoginPresenter @AssistedInject constructor() : Presenter<LoginUiState> {
    @Composable
    override fun present(): LoginUiState {
        return LoginUiState
    }

    @CircuitInject(LoginScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(
            // navigator: Navigator,
            // screen: HomeScreen
        ): LoginPresenter
    }
}

object LoginUiState: CircuitUiState

@Composable
@CircuitInject(LoginScreen::class, ActivityRetainedComponent::class)
fun LoginUi(state: LoginUiState, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Orange50),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "임시로 생성한 로그인 화면",
            color = NotiTheme.colors.contentBrand
        )
    }
}
