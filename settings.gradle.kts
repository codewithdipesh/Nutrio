pluginManagement {
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

rootProject.name = "nutrio"
include(":app")
include(":core")
include(":onboarding")
include(":onboarding:onboarding_domain")
include(":onboarding:onboarding_presentation")
include(":tracking")
include(":tracking:tracker_data")
include(":tracking:tracker_domain")
include(":tracking:tracker_presentation")
include(":core-ui")
