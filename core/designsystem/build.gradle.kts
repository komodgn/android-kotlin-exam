plugins {
    alias(libs.plugins.multi.module.android.presentation.ui)
}

android {
    namespace = "com.example.noti.core.designsystem"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.logger)

//    libs.androidx.splash,
//
//    libs.coil.compose,
//    libs.bundles.landscapist,
}