pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "Bitgoeul-Android"
include(":app")
include(":core:network")
include(":core:data")
include(":core:datastore")
include(":core:design-system")
include(":core:domain")
include(":core:ui")
include(":core:model")

include(":feature:login")
include(":feature:lecture")
include(":feature:sign-up")
include(":feature:student-activity")
include(":feature:my-page")
include(":feature:main")
include(":feature:post")
include(":feature:club")
include(":feature:email")
include(":feature:certification")
gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))
