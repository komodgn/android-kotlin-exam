import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import com.example.noti.convention.applyPlugins
import com.example.noti.convention.libs

class AndroidHiltConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                "com.google.dagger.hilt.android",
                "kotlin-kapt"
            )

            dependencies {
                "implementation"(libs.findLibrary("hilt.android").get())
                "kapt"(libs.findLibrary("hilt.android.compiler").get())
            }
        }
    }
}