plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}
gradlePlugin {
    plugins {
        create("domainModule") {
            id = "plugin.DomainModulePlugin"
            implementationClass = "plugin.DomainModulePlugin"
        }
    }
}
tasks.withType<ProcessResources> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

dependencies {
    compileOnly("org.jetbrains.kotlin.multiplatform:org.jetbrains.kotlin.multiplatform.gradle.plugin:1.9.24")
}
