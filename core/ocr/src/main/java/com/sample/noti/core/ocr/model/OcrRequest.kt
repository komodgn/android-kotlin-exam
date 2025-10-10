package com.sample.noti.core.ocr.model

import kotlinx.serialization.Serializable

@Serializable
data class OcrRequest(
    val requests: List<AnnotateImageRequest>
)

@Serializable
data class AnnotateImageRequest(
    val image: VisionImage,
    val features: List<Feature>
)

@Serializable
data class VisionImage(
    val content: String
)

@Serializable
data class Feature(
    val type: String = "TEXT_DETECTION"
)