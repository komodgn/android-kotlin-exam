plugins {
    alias(libs.plugins.multi.module.android.library)
}

android {
    namespace = "com.sample.noti.core.data.api"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.logger)
}