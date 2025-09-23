plugins {
    alias(libs.plugins.multi.module.android.feature)
}

android {
    namespace = "com.example.noti.feature.main"
}

dependencies {
    // TODO: feature/* 모듈 추가

    implementation(libs.androidx.activity.compose)
}