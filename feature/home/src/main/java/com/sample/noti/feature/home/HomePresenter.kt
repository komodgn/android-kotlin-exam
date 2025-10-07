package com.sample.noti.feature.home

import androidx.compose.runtime.Composable
import com.sample.noti.feature.screens.HomeScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class HomePresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    // TODO: Add Analytics Helper
    // TODO: Add Repository
) : Presenter<HomeUiState> {

    @Composable
    override fun present(): HomeUiState {

        fun handleEvent(event: HomeUiEvent) {
            when (event) {
                is HomeUiEvent.OnTabSelected -> {
                    navigator.resetRoot(
                        newRoot = event.tab.screen,
                        saveState = true,
                        restoreState = true
                    )
                }
            }
        }

        return HomeUiState(
            eventSink = ::handleEvent
        )
    }

    @AssistedFactory
    @CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
    interface Factory {
        fun create(
             navigator: Navigator,
        ): HomePresenter
    }
}
