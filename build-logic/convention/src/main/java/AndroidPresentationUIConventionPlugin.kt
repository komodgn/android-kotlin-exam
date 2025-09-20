/* home, login, board 등 화면을 담당하는 UI Layer 모듈에 적용되는 컨벤션 플러그인 */

import com.example.noti.convention.addUILayerDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidPresentationUIConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("multi.module.android.library.compose")
            }

            dependencies {
                addUILayerDependencies(target)
            }
        }
    }
}