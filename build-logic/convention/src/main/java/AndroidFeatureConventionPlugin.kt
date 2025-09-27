import com.sample.noti.convention.applyPlugins
import com.sample.noti.convention.libs
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                "multi.module.android.presentation.ui",
                "multi.module.android.hilt"
            )

            dependencies {
                "implementation"(project(":core:designsystem"))
                "implementation"(project(":core:data:api"))
                "implementation"(project(":core:common"))
                "implementation"(project(":core:model"))
                "implementation"(project(":core:ui"))
                "implementation"(project(":feature:screens"))

//                implementation(libs.compose.effects)
//
//                api(libs.circuit.codegen.annotation)
                "ksp"(libs.findLibrary("circuit.codegen.ksp").get())
                "implementation"(libs.findBundle("circuit").get())
            }
        }
    }
}