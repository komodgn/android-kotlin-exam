plugins {
    alias(libs.plugins.multi.module.android.library.compose)
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

    implementation(libs.logger)
    testImplementation(projects.core.testing)
}