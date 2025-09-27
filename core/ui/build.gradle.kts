plugins {
    alias(libs.plugins.multi.module.android.library.compose)
}

android {
    namespace = "com.sample.noti.core.ui"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.common)

    implementation(libs.compose.keyboard.state)
    implementation(libs.compose.effects)

    implementation(libs.logger)
}