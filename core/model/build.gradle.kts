plugins {
    alias(libs.plugins.multi.module.jvm.library)
}

dependencies {
    compileOnly(
        libs.compose.stable.marker,
    )
}