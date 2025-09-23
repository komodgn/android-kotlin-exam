plugins {
    alias(libs.plugins.multi.module.android.application.compose)
    alias(libs.plugins.multi.module.android.hilt)
}

android {
    namespace = "com.example.noti.template"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(projects.core.presentation.designsystem)
    // TODO: core:* 모듈 추가 - common, domain, data, network ..

    implementation(projects.feature.main)
    // TODO: feature:* 모듈 추가

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
}