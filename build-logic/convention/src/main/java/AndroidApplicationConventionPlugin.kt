import com.android.build.api.dsl.ApplicationExtension
import com.example.noti.convention.configureKotlinAndroid
import com.example.noti.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrations.kotlin.android")
            }

            extensions.configure<ApplicationExtention> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }
                configureKotlinAndroid(this)

                configureBuildTypes(
                    commonExtension = this
                )
            }
        }
    }
}