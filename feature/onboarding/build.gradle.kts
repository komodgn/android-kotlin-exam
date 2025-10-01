plugins {
    alias(libs.plugins.multi.module.android.feature)
    alias(libs.plugins.kotlin.serialization)
//    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.sample.noti.feature.onboarding"
}

ksp {
    arg("circuit.codegen.mode", "hilt")
}

dependencies {
    implementation(libs.logger)
}