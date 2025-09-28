package com.sample.noti.feature.screens

import com.slack.circuit.runtime.screen.Screen
import kotlinx.android.parcel.Parcelize


abstract class NotiScreen(val name: String) : Screen {
    override fun toString(): String = name
}

@Parcelize
data object SplashScreen : NotiScreen(name = ScreenNames.SPLASH)