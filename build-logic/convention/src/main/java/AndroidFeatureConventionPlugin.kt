import com.example.noti.convention.applyPlugins
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                "multi.module.android.library.compose",
                "multi.module.android.hilt"
            )

            dependencies {
                // TODO: presentation 폴더 제거 리팩토링
                "implementation"(project(":core:presentation:designsystem"))
                // TODO: core:* 모듈 생성 및 추가 - navigation, ui, common, domain, ..
            }
        }
    }
}