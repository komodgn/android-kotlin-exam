plugins {
    alias(libs.plugins.multi.module.android.presentation.ui)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.noti.home.presentation"
}

dependencies {
    implementation(projects.home.domain)
}