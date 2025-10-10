package com.sample.noti.feature.screens.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sample.noti.feature.screens.HomeScreen
import com.sample.noti.feature.screens.FaceScreen
import com.sample.noti.feature.screens.GraphScreen
import com.sample.noti.feature.screens.R
import com.slack.circuit.runtime.screen.Screen

enum class MainTab(
    @DrawableRes val iconResId: Int,
    @DrawableRes val selectedIconResId: Int,
    @StringRes val labelResId: Int,
    internal val description: String,
    val screen: Screen,
) {
    Friends(
        iconResId = R.drawable.ic_face,
        selectedIconResId = R.drawable.ic_selected_face,
        labelResId = R.string.face_label,
        description = "Face Icon",
        screen = FaceScreen,
    ),
    HOME(
        iconResId = R.drawable.ic_home,
        selectedIconResId = R.drawable.ic_selected_home,
        labelResId = R.string.home_label,
        description = "Home Icon",
        screen = HomeScreen,
    ),
    GRAPH(
        iconResId = R.drawable.ic_graph,
        selectedIconResId = R.drawable.ic_selected_graph,
        labelResId = R.string.graph_label,
        description = "Graph Icon",
        screen = GraphScreen,
    )
}