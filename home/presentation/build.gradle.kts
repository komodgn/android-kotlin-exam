plugins {
    alias(libs.plugins.multi.module.android.library.compose)
}

android {
    namespace = "com.example.noti.home.presentation"
}

dependencies {
    implementation(projects.home.domain)
    implementation(projects.core.presentation.designsystem)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}