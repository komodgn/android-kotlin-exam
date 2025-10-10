package com.sample.noti.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sample.noti.core.designsystem.ComponentPreview
import com.sample.noti.core.designsystem.theme.HomeBg
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.feature.home.R

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(HomeBg)
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .width(NotiTheme.spacing.spacing4)
        )
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "Noti Logo"
        )
        Spacer(
            modifier = Modifier
                .width(NotiTheme.spacing.spacing12)
        )
        // TODO: Add IconButton
    }
}

@ComponentPreview
@Composable
private fun HomeHeaderPreview() {
    NotiTheme {
        HomeHeader(

        )
    }
}