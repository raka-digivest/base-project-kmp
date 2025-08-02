package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class PresentationModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

        val koinCore = libs.findLibrary("koin-core").get()
        val voyagerNavigator = libs.findLibrary("voyager-navigator").get()
        val voyagerTransitions = libs.findLibrary("voyager-transitions").get()
        val voyagerKoin = libs.findLibrary("voyager-koin").get()
        val kermit = libs.findLibrary("kermit").get()

        //val compose = libs.findBundle("compose").get()

        project.extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.getByName("commonMain").dependencies {
                api(koinCore)
                implementation(voyagerNavigator)
                implementation(voyagerTransitions)
                implementation(voyagerKoin)
                implementation(kermit)

                //implementation(compose)
            }
        }
    }
}
