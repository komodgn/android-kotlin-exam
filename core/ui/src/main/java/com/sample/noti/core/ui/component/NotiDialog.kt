package com.sample.noti.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sample.noti.core.designsystem.theme.NotiTheme
import androidx.compose.material3.TextButton
import com.sample.noti.core.designsystem.ComponentPreview

@Composable
fun NotiDialog(
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null,
    confirmButtonText: String,
    onConfirmRequest: () -> Unit,
    dismissButtonText: String? = null,
    onDismissRequest: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column (
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(24.dp, 24.dp, 24.dp, 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(NotiTheme.spacing.spacing4))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                dismissButtonText?.let {
                    TextButton(
                        onClick = onDismissRequest,
                    ) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelLarge,
                            color = NotiTheme.colors.contentSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                TextButton(
                    onClick = onConfirmRequest,
                ) {
                    Text(
                        text = confirmButtonText,
                        style = MaterialTheme.typography.labelLarge,
                        color = NotiTheme.colors.contentBrand
                    )
                }
            }
        }
    }
}

@ComponentPreview
@Composable
fun NotiDialogPreview() {
    NotiTheme {
        NotiDialog(
            title = "설정 알림",
            description = "앱 설정 화면으로 이동하여 권한을 허용해주세요.",
            confirmButtonText = "설정으로 이동",
            onConfirmRequest = { },
            dismissButtonText = "취소",
            onDismissRequest = { }
        )
    }
}