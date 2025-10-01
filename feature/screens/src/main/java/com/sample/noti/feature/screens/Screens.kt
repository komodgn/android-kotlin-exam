package com.sample.noti.feature.screens

import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

abstract class NotiScreen(val name: String) : Screen {
    override fun toString(): String = name
}

@Parcelize
data object SplashScreen : NotiScreen(name = ScreenNames.SPLASH)

//object SplashScreen : Screen, Parcelable

@Parcelize
data object OnboardingScreen : NotiScreen(name = ScreenNames.ONBOARDING)

@Parcelize
data object HomeScreen : NotiScreen(name = ScreenNames.HOME)

@Parcelize
data object LoginScreen : NotiScreen(name = ScreenNames.LOGIN)