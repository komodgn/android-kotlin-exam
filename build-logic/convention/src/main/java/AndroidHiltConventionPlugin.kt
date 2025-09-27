import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import com.sample.noti.convention.applyPlugins
import com.sample.noti.convention.libs

class AndroidHiltConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                "com.google.dagger.hilt.android",
                "com.google.devtools.ksp",
            )

            dependencies {
                "implementation"(libs.findLibrary("hilt.android").get())
                "ksp"(libs.findLibrary("hilt.android.compiler").get())
            }
        }
    }
}