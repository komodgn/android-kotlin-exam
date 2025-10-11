plugins {
    alias(libs.plugins.multi.module.android.presentation.ui)
}

android {
    namespace = "com.sample.noti.core.designsystem"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.logger)

//    libs.androidx.splash,
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
//    libs.bundles.landscapist,
}