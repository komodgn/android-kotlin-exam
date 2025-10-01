package com.sample.noti.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.core.designsystem.theme.Orange50
import com.sample.noti.feature.screens.OnboardingScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

// TODO: state, presenter 분리
class OnboardingPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator
) : Presenter<OnboardingUiState> {
    @Composable
    override fun present(): OnboardingUiState {
        return OnboardingUiState
    }
    @CircuitInject(OnboardingScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(
            navigator: Navigator
        ): OnboardingPresenter
    }
}

object OnboardingUiState: CircuitUiState

@Composable
@CircuitInject(OnboardingScreen::class, ActivityRetainedComponent::class)
fun OnboardingUi(state: OnboardingUiState, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Orange50),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "임시로 생성한 온보딩 화면",
            color = NotiTheme.colors.contentBrand
        )
    }
}
