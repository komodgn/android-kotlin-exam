import com.android.build.api.dsl.ApplicationExtension
import com.example.noti.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin: Plugin<Plugin> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.apply("multi.module.android.application")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }
}