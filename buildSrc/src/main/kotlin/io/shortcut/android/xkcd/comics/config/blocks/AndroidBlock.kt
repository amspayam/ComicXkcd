package io.shortcut.android.xkcd.comics.config.blocks

import io.shortcut.android.xkcd.comics.config.ApplicationId
import io.shortcut.android.xkcd.comics.extenstion.android
import org.gradle.api.JavaVersion
import org.gradle.api.Project


fun Project.setupAndroidBlock(isApplication: Boolean) {

    android.run {

        buildToolsVersion(Build.buildTools)
        compileSdkVersion(Build.compileSdk)

        defaultConfig {

            minSdkVersion(Build.minSdk)
            targetSdkVersion(Build.targetSdk)

            if (isApplication) {
                applicationId(ApplicationId.applicationId)
            }

            versionCode(ApplicationId.versionCode)
            versionName(ApplicationId.versionName)

            vectorDrawables.useSupportLibrary = true

            multiDexEnabled = true


            flavorDimensions("default")

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        android.buildFeatures.viewBinding = true

        testOptions {
            unitTests {
                isReturnDefaultValues = true
                isIncludeAndroidResources = true
            }
            animationsDisabled = true
            resources
        }

        dexOptions {
            incremental = true
            javaMaxHeapSize = "4g"
        }

        compileOptions {
            sourceCompatibility = Build.javaVersion
            targetCompatibility = Build.javaVersion
        }
    }
}

object Build {

    const val minSdk = 23
    const val compileSdk = 30
    const val targetSdk = 30
    const val buildTools = "30.0.3"

    val javaVersion = JavaVersion.VERSION_1_8
}

