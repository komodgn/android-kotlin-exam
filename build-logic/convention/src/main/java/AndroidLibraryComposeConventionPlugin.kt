/*  Compose 관련 기능이 있지만 화면은 없는 모듈을 위한 컨벤션 플러그인 */

import com.android.build.api.dsl.LibraryExtension
import com.example.noti.convention.configureKotlinAndroid
import com.example.noti.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("multi.module.android.library")
            }

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }
}