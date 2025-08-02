package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class DomainModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val libs = project.extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

        val koinCore = libs.findLibrary("koin.core").get()

        project.extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.getByName("commonMain").dependencies {
                api(koinCore)
            }
        }
    }
}
