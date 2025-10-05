import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.multi.module.android.library)
    alias(libs.plugins.multi.module.android.retrofit)
    alias(libs.plugins.multi.module.android.hilt)
}

android {
    namespace = "com.sample.noti.core.network"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "SERVER_BASE_URL", getServerBaseUrl("DEBUG_SERVER_BASE_URL"))
        }

        release {
            buildConfigField("String", "SERVER_BASE_URL", getServerBaseUrl("RELEASE_SERVER_BASE_URL"))
        }
    }
}

dependencies {
    implementation(projects.core.datastore.api)

    implementation(libs.logger)
}

fun getServerBaseUrl(propertyKey: String): String {
    return gradleLocalProperties(rootDir, providers).getProperty(propertyKey)
}