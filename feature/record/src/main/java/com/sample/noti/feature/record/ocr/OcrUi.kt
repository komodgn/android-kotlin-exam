package com.sample.noti.feature.record.ocr

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import android.net.Uri
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.sample.noti.core.designsystem.ComponentPreview
import com.sample.noti.core.designsystem.theme.Neutral900
import dagger.hilt.android.components.ActivityRetainedComponent
import com.sample.noti.feature.screens.OcrScreen
import com.sample.noti.core.designsystem.theme.NotiTheme
import com.sample.noti.core.designsystem.theme.White
import com.sample.noti.core.ui.NotiScaffold
import com.slack.circuit.codegen.annotations.CircuitInject
import com.sample.noti.core.ui.component.NotiDialog
import com.sample.noti.feature.record.R
import java.io.File

@CircuitInject(OcrScreen::class, ActivityRetainedComponent::class)
@Composable
fun OcrUi(
    state: OcrUiState,
    modifier: Modifier
) {
    when (state.currentUi) {
        OcrUi.CAMERA -> CameraPreview(
            state = state,
            modifier = modifier
        )

        OcrUi.RESULT -> OcrResult(
            state = state,
            modifier = modifier
        )
    }
}

@Composable
private fun CameraPreview(
    state: OcrUiState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val permission = Manifest.permission.CAMERA

    val isGranted by produceState(
        initialValue = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED,
        key1 = lifecycleOwner, // lifecycle 변경 시 재설정
    ) {
        // 최초 동기화
        value = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

        // 포그라운드 복귀 시 OS 권한 동기화
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                value = ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
                if (value) {
//                    state.eventSink(OcrUiEvent.OnHidePermissionDialog)
                } else {
                    state.eventSink(OcrUiEvent.OnShowPermissionDialog)
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        awaitDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { granted ->
        if (!granted) {
            state.eventSink(OcrUiEvent.OnShowPermissionDialog)
        }
    }
    val settingsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { _ -> }

    // 최초 진입 시 권한 요청
    LaunchedEffect(Unit) {
        if (!isGranted) {
            state.eventSink(OcrUiEvent.OnShowPermissionDialog)
            permissionLauncher.launch(permission)
        }
    }

    /**
     * Camera Controller
     */
    val cameraController = remember { LifecycleCameraController(context) }

    DisposableEffect(isGranted, lifecycleOwner, cameraController) {
        if (isGranted) {
            cameraController.bindToLifecycle(lifecycleOwner)
        }

        onDispose {
            cameraController.unbind()
        }
    }

    NotiScaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Neutral900,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (isGranted) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White)
                        .height(200.dp)
                        .align(Alignment.Center),
                ) {
                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = { context ->
                            PreviewView(context).apply {
                                layoutParams = LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                )
                                clipToOutline = true
                                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                                scaleType = PreviewView.ScaleType.FILL_CENTER
                                controller = cameraController
                            }
                        },
                    )
                }
            }

            Column(
                modifier = Modifier.align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = {
                        val executor = ContextCompat.getMainExecutor(context)
                        val photoFile = File.createTempFile("ocr_", ".jpg", context.cacheDir)
                        val output = ImageCapture.OutputFileOptions.Builder(photoFile).build()

                        cameraController.takePicture(
                            output,
                            executor,
                            object : ImageCapture.OnImageSavedCallback {
                                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                    state.eventSink(OcrUiEvent.OnImageCaptured(photoFile.toUri()))
                                }

                                override fun onError(exception: ImageCaptureException) {

                                }
                            },
                        )
                    },
                    modifier = Modifier.size(72.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NotiTheme.colors.bgPrimary,
                        contentColor = White,
                    ),
                    contentPadding = PaddingValues(NotiTheme.spacing.spacing0),
                ) {
                    Text("칸쵸 찾기")
                }
                Spacer(modifier = Modifier.height(NotiTheme.spacing.spacing4))
            }
        }
    }

    if (state.isPermissionDialogVisible) {
        NotiDialog(
            title = stringResource(R.string.permission_dialog_title),
            description = stringResource(R.string.permission_dialog_description),
            confirmButtonText = stringResource(R.string.permission_dialog_move_to_settings),
            onConfirmRequest = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                settingsLauncher.launch(intent)
            },
        )
    }
}

@Composable
private fun OcrResult(
    state: OcrUiState,
    modifier: Modifier = Modifier
) {
    NotiScaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = White,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = NotiTheme.spacing.spacing5),
                verticalArrangement = Arrangement.spacedBy(NotiTheme.spacing.spacing2),
            ) {
                item {
                    Spacer(modifier = Modifier.height(NotiTheme.spacing.spacing1))
                }

                items(state.textList.size) { index ->
                    Text(
                        text = state.textList[index],
                    )
                }
            }
        }
    }
}

@ComponentPreview
@Composable
private fun OcrCameraPreview() {
    NotiTheme {
        CameraPreview(
            state = OcrUiState(
                eventSink = {}
            ),
        )
    }
}

@ComponentPreview
@Composable
private fun OcrResultPreview() {
    NotiTheme {
        OcrResult(
            state = OcrUiState(
                eventSink = {}
            ),
        )
    }
}
