plugins {
    alias(libs.plugins.multi.module.jvm.library)
}

dependencies {
    api(libs.junit)
    api(libs.truth)

    api(libs.mockk.jvm)
}
