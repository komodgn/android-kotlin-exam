package com.sample.noti.core.ocr.model

import kotlinx.serialization.Serializable

@Serializable
data class OcrResponse(
    val response: List<AnnotateImageResponse>
)

@Serializable
data class AnnotateImageResponse(
    val fullTextAnnotation: List<FullTextAnnotation>? = null
)

@Serializable
data class FullTextAnnotation(
    val text: String? = null
)