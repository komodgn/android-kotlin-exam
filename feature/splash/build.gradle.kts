plugins {
    alias(libs.plugins.multi.module.android.feature)
}

android {
    namespace = "com.sample.noti.feature.splash"
}

ksp {
    arg("circuit.codegen.mode", "hilt")
}

dependencies {
    implementation(libs.compose.system.ui.controller)
    implementation(libs.logger)
}