import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.multi.module.android.feature)
    alias(libs.plugins.multi.module.android.retrofit)
    alias(libs.plugins.multi.module.android.hilt)
}

android {
    namespace = "com.sample.noti.core.ocr"

    defaultConfig {
        buildConfigField("String", "CLOUD_VISION_API_KEY", getApiKey("CLOUD_VISION_API_KEY"))
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.logger)
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir, providers).getProperty(propertyKey)
}