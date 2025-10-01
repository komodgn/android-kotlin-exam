package com.sample.noti.feature.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.sample.noti.core.data.api.repository.AuthRepository
import com.sample.noti.core.data.api.repository.RemoteConfigRepository
import com.sample.noti.core.data.api.repository.UserRepository
import com.sample.noti.core.model.AutoLoginState
import com.sample.noti.core.model.OnboardingState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.collectAsRetainedState
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.sample.noti.feature.screens.SplashScreen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.launch
import com.orhanobut.logger.Logger
import com.sample.noti.feature.screens.HomeScreen
import com.sample.noti.feature.screens.LoginScreen
import com.sample.noti.feature.screens.OnboardingScreen
import com.slack.circuitx.effects.ImpressionEffect
import kotlinx.coroutines.delay

class SplashPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val remoteConfigRepository: RemoteConfigRepository,
    // TODO: Add AnalyticsHelper
) : Presenter<SplashUiState> {

    @Composable
    override fun present(): SplashUiState {
        val scope = rememberCoroutineScope()
        val onboardingState by userRepository.onboardingState.collectAsRetainedState(OnboardingState.IDLE)
        val autoLoginState by authRepository.autoLoginState.collectAsRetainedState(AutoLoginState.IDLE)
        var isForceUpdateDialogVisible by rememberRetained { mutableStateOf(false)  }
        var sideEffect by rememberRetained { mutableStateOf<SplashSideEffect?>(null) }

        fun checkTermsAgreement() {
            scope.launch {
                userRepository.getUserProfile()
                    .onSuccess { userProfile ->
                        if (userProfile.termsAgreed) {
                            // Navigate to Home
                            navigator.resetRoot(HomeScreen)
                        } else {
                            // Navigate to Login
                            navigator.resetRoot(LoginScreen)
                        }
                    }
                    .onFailure { exception ->
                        // Handle error
                    }
            }
        }

        fun proccedToNextScreen() {
            when (onboardingState) {
                OnboardingState.NOT_COMPLETED -> {
                    navigator.resetRoot(OnboardingScreen)
                }
                OnboardingState.COMPLETED -> {
                    when (autoLoginState) {
                        AutoLoginState.LOGGED_IN -> {
                            checkTermsAgreement()
                        }
                        AutoLoginState.NOT_LOGGED_IN -> {
                            navigator.resetRoot(LoginScreen)
                        }
                        AutoLoginState.IDLE -> {
                            // Wait for auto login state change
                        }
                    }
                }
                OnboardingState.IDLE -> {
                    // Wait for onboarding state change
                }
            }
        }

        fun checkForceUpdate() {
            scope.launch {
                remoteConfigRepository.shouldUpdate()
                    .onSuccess { shouldUpdate ->
                        if (shouldUpdate) {
                            isForceUpdateDialogVisible = true
                        } else {
                            proccedToNextScreen()
                        }
                    }
                    .onFailure { exception ->
                        Logger.e("{$exception.message}")
                        proccedToNextScreen()
                    }
            }
        }

        fun handleEvent(event: SplashUiEvent) {
            when (event) {
                SplashUiEvent.OnUpdateButtonClick -> {
                    sideEffect = SplashSideEffect.NavigateToPlayStore
                }
                SplashUiEvent.InitSideEffect -> {
                    sideEffect = null
                }
            }
        }

        LaunchedEffect(onboardingState, autoLoginState) {
          delay(1000L)

            if (onboardingState == OnboardingState.IDLE || autoLoginState == AutoLoginState.IDLE) {
                return@LaunchedEffect
            }

            checkForceUpdate()
        }

        ImpressionEffect {
            // TODO: [Analytics Helper Logging]
        }

        return SplashUiState(
            isForceUpdateDialogVisible = isForceUpdateDialogVisible,
            eventSink = ::handleEvent,
            sideEffect = sideEffect
        )
    }

    @CircuitInject(SplashScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): SplashPresenter
    }
}

