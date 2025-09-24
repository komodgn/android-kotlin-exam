import com.example.noti.convention.applyPlugins
import com.example.noti.convention.libs
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.dependencies

class AndroidRetrofitConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            applyPlugins(
                "multi.module.kotlin.library.serialization"
            )

            dependencies {
                "implementation"(libs.findLibrary("retrofit").get())
                "implementation"(libs.findLibrary("retrofit.kotlinx.serialization.converter").get())
                "implementation"(libs.findLibrary("okhttp.logging.interceptor").get())
            }
        }
    }
}