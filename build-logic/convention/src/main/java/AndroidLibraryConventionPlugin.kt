/*  Compose가 없는 AndroidLibrary를 위한 컨벤션 플러그인 */

import com.android.build.api.dsl.LibraryExtension
import com.sample.noti.convention.configureBuildTypes
import com.sample.noti.convention.configureKotlinAndroid
import com.sample.noti.convention.ExtensionType
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                configureBuildTypes(
                    commonExtension = this, 
                    extensionType = ExtensionType.LIBRARY
                )

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro") 
                }
            }
        }
    }
}