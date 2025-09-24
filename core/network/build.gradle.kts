plugins {
    alias(libs.plugins.multi.module.android.library)
    alias(libs.plugins.multi.module.android.retrofit)
    alias(libs.plugins.multi.module.android.hilt)
}

android {
    namespace = "com.example.noti.core.network"
}

dependencies {
    implementation(libs.logger)
}