package com.sample.noti.core.ocr.service

import com.sample.noti.core.ocr.model.OcrRequest
import com.sample.noti.core.ocr.model.OcrResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OcrService {
    @POST("v1/images:annotate")
    suspend fun annotateImage(
        @Header("X-Goog-Api-Key") apiKey: String,
        @Body body: OcrRequest
        ): OcrResponse
}