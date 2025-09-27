/*  Compose 관련 기능이 있지만 화면은 없는 모듈을 위한 컨벤션 플러그인 */

import com.android.build.api.dsl.LibraryExtension
import com.sample.noti.convention.applyPlugins
import com.sample.noti.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                "multi.module.android.library",
                "org.jetbrains.kotlin.plugin.compose"
            )

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }
}