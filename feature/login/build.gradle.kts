plugins {
    alias(libs.plugins.multi.module.android.feature)
    alias(libs.plugins.kotlin.serialization)
//    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.example.noti.feature.login"
}

dependencies {
//    implementation(libs.kakao.auth)

    implementation(libs.logger)
}