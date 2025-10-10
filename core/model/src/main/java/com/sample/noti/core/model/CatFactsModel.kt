package com.sample.noti.core.model

import androidx.compose.runtime.Stable

@Stable
data class CatFactsModel (
    val id: Int,
    val userId: Int,
    val title: String = "",
    val body: String = "",
)