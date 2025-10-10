package com.sample.noti.feature.screens.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.sample.noti.core.designsystem.ComponentPreview
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.core.designsystem.theme.Orange100
import com.sample.noti.core.designsystem.theme.Orange50
import com.sample.noti.core.designsystem.theme.White
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainBottomBar(
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = NotiTheme.spacing.spacing12,
                    topEnd = NotiTheme.spacing.spacing12,
                ),
            )
            .background(White)
            .border(
                width = 1.dp,
                color = NotiTheme.colors.contentBrand,
                shape = RoundedCornerShape(
                    topStart = NotiTheme.spacing.spacing12,
                    topEnd = NotiTheme.spacing.spacing12,
                ),
            )
    ) {
        Row(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                MainBottomBarItem(
                    tab = tab,
                    selected = tab == currentTab,
                    onClick = {
                        if (tab != currentTab) {
                            onTabSelected(tab)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun RowScope.MainBottomBarItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .selectable(
                selected = selected,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .height(NotiTheme.spacing.spacing2)
            )
            Icon(
                imageVector = if (selected) ImageVector.vectorResource(tab.selectedIconResId)
                else ImageVector.vectorResource(tab.iconResId),
                contentDescription = tab.description,
                tint = Color.Unspecified,
            )
            Spacer(
                modifier = Modifier
                    .height(NotiTheme.spacing.spacing2)
            )
            Text(
                text = stringResource(tab.labelResId),
                style = NotiTheme.typography.caption2Regular,
                color = if (selected) NotiTheme.colors.contentPrimary else NotiTheme.colors.contentSecondary,
            )
        }
    }
}

@ComponentPreview
@Composable
private fun MainBottomBarPreview() {
    NotiTheme {
        MainBottomBar(
            tabs = MainTab.entries.toImmutableList(),
            currentTab = MainTab.HOME,
            onTabSelected = {}
        )
    }
}