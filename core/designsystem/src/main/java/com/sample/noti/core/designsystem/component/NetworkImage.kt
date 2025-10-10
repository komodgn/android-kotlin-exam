package com.sample.noti.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sample.noti.core.designsystem.ComponentPreview
import com.sample.noti.core.designsystem.theme.NotiTheme

@Composable
fun NetworkImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
    )
}

@ComponentPreview
@Composable
fun NetworkImagePreview() {
    NotiTheme {
        NetworkImage(
            imageUrl = "https://cdn.pixabay.com/photo/2016/11/18/21/30/open-sign-1836961_1280.jpg",
            contentDescription = "네트워크 이미지",
        )
    }
}