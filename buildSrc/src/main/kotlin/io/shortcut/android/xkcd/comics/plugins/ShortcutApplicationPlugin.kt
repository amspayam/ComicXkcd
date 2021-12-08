package io.shortcut.android.xkcd.comics.plugins

import io.shortcut.android.xkcd.comics.config.blocks.setupAndroidBlock
import io.shortcut.android.xkcd.comics.config.blocks.setupBuildTypesBlock
import io.shortcut.android.xkcd.comics.config.blocks.setupFlavorBlock
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

class ShortcutApplicationPlugin : Plugin<Project> {

    override fun apply(project: Project) =
        with(project) {
            applyPlugins()
            androidConfig()
        }

    private fun Project.applyPlugins() {
        plugins.run {
            apply("com.android.application")
            apply("kotlin-android")
            apply("com.github.ben-manes.versions")
            apply("androidx.navigation.safeargs")
            apply("kotlin-parcelize")
            apply("kotlin-kapt")
        }
    }

    private fun Project.androidConfig() {

        setupAndroidBlock(isApplication = true)
        setupBuildTypesBlock(isApplication = true)
        setupFlavorBlock(isApplication = true)

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

}