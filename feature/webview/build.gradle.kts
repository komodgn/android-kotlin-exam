plugins {
    alias(libs.plugins.multi.module.android.feature)
}

android {
    namespace = "com.sample.noti.feature.webview"
}

ksp {
    arg("circuit.codegen.mode", "hilt")
}

dependencies {
    implementation(libs.logger)
}