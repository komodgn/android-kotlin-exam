plugins {
    alias(libs.plugins.multi.module.android.library)
    alias(libs.plugins.multi.module.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sample.noti.core.data.impl"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.core.data.api)
    implementation(projects.core.datastore.api)

    platform(libs.firebase.bom)
    implementation(libs.firebase.remote.config)

    implementation(libs.logger)
}