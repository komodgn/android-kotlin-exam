plugins {
    alias(libs.plugins.multi.module.android.library)
}

android {
    namespace = "com.example.noti.core.datastore.api"
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.kotlinx.coroutines.core)
}