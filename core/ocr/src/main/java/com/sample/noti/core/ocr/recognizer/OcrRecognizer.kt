package com.sample.noti.core.ocr.recognizer

import android.net.Uri
import com.sample.noti.core.common.utils.runSuspendCatching
import com.sample.noti.core.ocr.model.AnnotateImageRequest
import com.sample.noti.core.ocr.model.Feature
import com.sample.noti.core.ocr.model.OcrRequest
import com.sample.noti.core.ocr.model.OcrResponse
import com.sample.noti.core.ocr.model.VisionImage
import com.sample.noti.core.ocr.service.OcrService
import com.sample.noti.core.ocr.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.Base64
import javax.inject.Inject

class OcrRecognizer @Inject constructor(
    private val ocrService: OcrService
) {
    suspend fun recognizeText(imageUri: Uri): Result<OcrResponse> = runSuspendCatching {
        withContext(Dispatchers.IO) {
            val filePath = imageUri.path ?: throw IllegalArgumentException("Invalid path")
            val file = File(filePath)
            val byte = file.readBytes()
            val base64Image = Base64.getEncoder().encodeToString(byte)

            val body = OcrRequest(
                requests = listOf(
                    AnnotateImageRequest(
                        image = VisionImage(base64Image),
                        features = listOf(Feature("TEXT_DETECTION"))
                    )
                )
            )

            ocrService.annotateImage(
                apiKey = BuildConfig.CLOUD_VISION_API_KEY,
                body = body
            )
        }
    }
}