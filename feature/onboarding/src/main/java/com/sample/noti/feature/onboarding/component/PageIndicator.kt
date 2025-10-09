package com.sample.noti.feature.onboarding.component;

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sample.noti.core.designsystem.ComponentPreview
import com.sample.noti.core.designsystem.theme.Neutral300
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.feature.onboarding.ONBOARDING_PAGE_COUNT

@Composable
internal fun PageIndicator(
    pageCount: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { it ->
                val color = if (pagerState.currentPage == it) NotiTheme.colors.contentBrand else Neutral300

                Box(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .clip(CircleShape)  // 먼저 자르고 배경색 입혀야 됨(순서 중요)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}

@ComponentPreview
@Composable
private fun PageIndicator() {
    NotiTheme {
        PageIndicator(
            pageCount = ONBOARDING_PAGE_COUNT,
            pagerState = rememberPagerState(pageCount = { ONBOARDING_PAGE_COUNT })
        )
    }
}
