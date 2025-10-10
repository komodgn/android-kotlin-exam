package com.sample.noti.feature.onboarding

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.sample.noti.core.common.analytics.AnalyticsHelper
import com.sample.noti.core.data.api.repository.UserRepository
import com.sample.noti.feature.screens.LoginScreen
import com.sample.noti.feature.screens.OnboardingScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuitx.effects.ImpressionEffect
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.launch

const val ONBOARDING_PAGE_COUNT = 3

class OnboardingPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val userRepository: UserRepository,
    private val analyticsHelper: AnalyticsHelper
) : Presenter<OnboardingUiState> {
    @Composable
    override fun present(): OnboardingUiState {
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = {ONBOARDING_PAGE_COUNT})

        fun handleEvent(event: OnboardingUiEvent) {
            when (event) {
                is OnboardingUiEvent.OnNextButtonClick -> {
                    if (event.currentPage == ONBOARDING_PAGE_COUNT - 1) {
                        scope.launch {
                            userRepository.setOnboardingCompleted(true)
                            navigator.resetRoot(LoginScreen)
                        }
                    } else {
                        pagerState.let { state ->
                            scope.launch {
                                state.animateScrollToPage(event.currentPage + 1)
                            }
                        }
                    }
                }

                is OnboardingUiEvent.OnPreviousButtonClick -> {

                }

                is OnboardingUiEvent.OnSkipButtonClick -> {
                    scope.launch {
                        userRepository.setOnboardingCompleted(true)
                        navigator.resetRoot(LoginScreen)
                    }
                }
            }
        }

        ImpressionEffect {
            analyticsHelper.logScreenView(OnboardingScreen.name)
        }

        return OnboardingUiState(
            pagerState = pagerState,
            eventSink = ::handleEvent
        )
    }
    @CircuitInject(OnboardingScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(
            navigator: Navigator
        ): OnboardingPresenter
    }
}