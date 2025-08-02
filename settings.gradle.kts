rootProject.name = "BaseProcject"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic/data-module")
    includeBuild("build-logic/domain-module")
    includeBuild("build-logic/presentation-module")
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}
include(":composeApp")
include(":baseProjectApp:data")
include(":baseProjectApp:domain")
include(":baseProjectApp:presentation")
include(":baseProjectApp")
include(":baseProjectApp:design-system")
include(":baseProjectApp:base")
