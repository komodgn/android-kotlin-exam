package com.sample.noti.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sample.noti.core.data.api.repository.CatFactsRepository
import com.sample.noti.feature.screens.HomeScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class HomePresenter @AssistedInject constructor(
    private val catFactsRepository: CatFactsRepository
) : Presenter<HomeUiState> {

    @Composable
    override fun present(): HomeUiState {
        var uiState by remember {
            mutableStateOf(HomeUiState(isLoading = true))
        }

        LaunchedEffect(Unit) {
            val result = catFactsRepository.getCatFacts()

            result.onSuccess { catFact ->
                uiState = HomeUiState(
                    isLoading = false,
                    title = catFact.title,
                    body = catFact.body
                )
            }.onFailure { throwable ->
                uiState = HomeUiState(
                    isLoading = false,
                    errorText = "데이터 로드 실패: ${throwable.message}"
                )
            }
        }

        return uiState
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
