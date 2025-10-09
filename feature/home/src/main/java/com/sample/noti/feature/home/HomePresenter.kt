package com.sample.noti.feature.home

import android.util.Log
import androidx.compose.runtime.Composable
import com.sample.noti.core.common.analytics.AnalyticsHelper
import com.sample.noti.feature.screens.HomeScreen
import com.sample.noti.feature.screens.OcrScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuitx.effects.ImpressionEffect
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class HomePresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val analyticsHelper: AnalyticsHelper
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

                is HomeUiEvent.OnTextScanButtonClick -> {
                    Log.d("Circuit", "OCR 버튼 클릭 이벤트 수신")
                    navigator.goTo(OcrScreen)
                }
            }
        }

        ImpressionEffect {
            analyticsHelper.logScreenView(HomeScreen.name)
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
