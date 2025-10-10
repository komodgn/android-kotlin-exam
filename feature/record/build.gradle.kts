plugins {
    alias(libs.plugins.multi.module.android.feature)
}

android {
    namespace = "com.sample.noti.feature.record"
}

ksp {
    arg("circuit.codegen.mode", "hilt")
}

dependencies {
    implementation(projects.core.ocr)

//    implementation(libs.androidx.activity.compose),
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)

    implementation(libs.logger)
}