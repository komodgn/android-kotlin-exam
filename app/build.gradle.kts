plugins {
    alias(libs.plugins.multi.module.android.application.compose)
    alias(libs.plugins.multi.module.android.hilt)
}

android {
    namespace = "com.sample.noti.template"
}

ksp {
    arg("circuit.codegen.mode", "hilt")
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(projects.core.ui)
    implementation(projects.core.ocr)
    implementation(projects.core.network)
    implementation(projects.core.data.api)
    implementation(projects.core.data.impl)
    implementation(projects.core.datastore.api)
    implementation(projects.core.datastore.impl)

    implementation(projects.feature.main)
    implementation(projects.feature.screens)
    implementation(projects.feature.splash)
    implementation(projects.feature.onboarding)
    implementation(projects.feature.login)
    implementation(projects.feature.home)
    implementation(projects.feature.webview)

//    implementation(libs.androidx.startup)
//    implementation(libs.coil.compose)
//    implementation(libs.kakao.auth)
    implementation(libs.androidx.activity.compose)
    implementation(libs.logger)

    implementation(libs.bundles.circuit)
}
