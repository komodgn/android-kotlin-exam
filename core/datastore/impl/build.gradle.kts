plugins {
    alias(libs.plugins.multi.module.android.library)
    alias(libs.plugins.multi.module.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.noti.core.datastore.impl"
}

dependencies {
    implementation(projects.core.datastore.api)
    implementation(projects.core.model)

    implementation(libs.androidx.datastore.preferences)

    implementation(libs.logger)
}