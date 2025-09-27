pluginManagement {

    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "noti"

include(":app")

include(":feature:main")
include(":feature:onboarding")
include(":feature:login")
include(":feature:home")
include(":feature:screens")

include(":core:model")
include(":core:network")
include(":core:ui")
include(":core:common")
include(":core:designsystem")
include(":core:data:api")
include(":core:data:impl")
include(":core:datastore:api")
include(":core:datastore:impl")
