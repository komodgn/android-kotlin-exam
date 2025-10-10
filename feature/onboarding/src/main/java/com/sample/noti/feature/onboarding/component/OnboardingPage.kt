package com.sample.noti.feature.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sample.noti.core.designsystem.ComponentPreview
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.core.designsystem.theme.Orange500
import com.sample.noti.feature.onboarding.R

@Composable
internal fun OnboardingPage(
    titleRes: Int,
    imageRes: Int,
    descRes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = Orange500,
            text = stringResource(titleRes),
            style = NotiTheme.typography.titleBmjua
        )
        Spacer(
            modifier = Modifier.height(
                NotiTheme.spacing.spacing2
            )
        )
        Image(
            modifier = Modifier.height(200.dp),
            contentDescription = "Onboarding Image",
            painter = painterResource(imageRes)
        )
        Spacer(
            modifier = Modifier.height(
                NotiTheme.spacing.spacing2
            )
        )
        Text(
            text = stringResource(descRes)
        )
        Spacer(
            modifier = Modifier.height(
                NotiTheme.spacing.spacing2
            )
        )
    }
}

@ComponentPreview
@Composable
private fun OnboardingPagePreview() {
    NotiTheme {
        OnboardingPage(
            titleRes = R.string.onboarding_first_page_title,
            imageRes = R.drawable.img_onboarding_first,
            descRes = R.string.onboarding_first_page_description,
        )
    }
}