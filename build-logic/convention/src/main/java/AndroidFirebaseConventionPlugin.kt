import com.sample.noti.convention.applyPlugins
import com.sample.noti.convention.libs
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.dependencies

class AndroidFirebaseConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            applyPlugins(
                "com.google.gms.google-services",
                "com.google.firebase.crashlytics"
            )

            dependencies {
                "implementation"(platform(libs.findLibrary("firebase.bom").get()))
                "implementation"(libs.findLibrary("firebase.analytics").get())
                "implementation"(libs.findLibrary("firebase.crashlytics").get())
            }
        }
    }
}