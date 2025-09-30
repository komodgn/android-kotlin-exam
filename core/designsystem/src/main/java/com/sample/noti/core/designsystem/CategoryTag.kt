package com.sample.noti.core.designsystem

import androidx.compose.ui.graphics.Color
import com.sample.noti.core.designsystem.theme.Blue300
import com.sample.noti.core.designsystem.theme.Blue500

enum class CategoryTag(
    val label: String,
    val textColor: Color,
    val bgColor: Color,
) {
    TOP("상의", Blue500, Blue300),
    OUTER("아우터",Blue500, Blue300),
    PANTS("바지",Blue500, Blue300),
    DRESS("원피스/스커트",Blue500, Blue300),
    SPORTS("스포츠/레저",Blue500, Blue300),
    INNER("속옷/홈웨어",Blue500, Blue300),
    SHOES("신발",Blue500, Blue300),
    BAG("가방",Blue500, Blue300),
    ACC("패션소품",Blue500, Blue300),
}