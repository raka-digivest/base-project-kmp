package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class DataModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val libs = project.extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

        val ktorCore = libs.findLibrary("ktor.core").get()
        val ktorJson = libs.findLibrary("ktor.json").get()
        val ktorLogging = libs.findLibrary("ktor.logging").get()
        val ktorNegotiation = libs.findLibrary("ktor.negotiation").get()
        val koinCore = libs.findLibrary("koin.core").get()

        project.extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.getByName("commonMain").dependencies {
                implementation(ktorCore)
                implementation(ktorJson)
                implementation(ktorLogging)
                implementation(ktorNegotiation)
                implementation(koinCore)
            }
        }
    }
}
