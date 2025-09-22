package com.example.noti.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * Project.libs 라는 속성을 만들어, 어떤 Gradle Project 객체든
 * libs라는 이름으로 Version Catalog에 접근할 수 있도록 함.
 */
val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * 여러 플러그인 추가를 위한 확장 함수
 */
internal fun Project.applyPlugins(vararg plugins: String) {
    plugins.forEach(pluginManager::apply)
}