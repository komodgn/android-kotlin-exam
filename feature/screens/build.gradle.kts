plugins {
    alias(libs.plugins.multi.module.android.library.compose)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.sample.noti.feature.screens"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.model)

    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.circuit.foundation)
//    implementation(libs.compose.shadow)

    implementation(libs.logger)

//    api(libs.circuit.runtime)
}