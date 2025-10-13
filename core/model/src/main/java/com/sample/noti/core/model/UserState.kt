package com.sample.noti.core.model

sealed interface UserState {
    data object Guest: UserState
    data object LoggedIn: UserState
}