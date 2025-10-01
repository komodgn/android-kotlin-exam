plugins {
    alias(libs.plugins.multi.module.android.feature)
}

android {
    namespace = "com.sample.noti.feature.main"
}

//ksp {
//    arg("circuit.codegen.mode", "hilt")
//}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.compose.system.ui.controller)

    implementation(libs.logger)
}