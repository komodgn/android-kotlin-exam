plugins {
    alias(libs.plugins.multi.module.android.library.compose)
    alias(libs.plugins.multi.module.android.hilt)
}

android {
    namespace = "com.sample.noti.core.common"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "PACKAGE_NAME", "\"${libs.versions.applicationId.get()}\"")
    }
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.kotlinx.collections.immutable)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    implementation(libs.logger)
    testImplementation(projects.core.testing)
}