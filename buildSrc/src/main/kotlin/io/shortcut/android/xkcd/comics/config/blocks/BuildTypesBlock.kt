package io.shortcut.android.xkcd.comics.config.blocks

import io.shortcut.android.xkcd.comics.extenstion.addConfigField
import io.shortcut.android.xkcd.comics.extenstion.addDoubleQuotation
import io.shortcut.android.xkcd.comics.extenstion.android
import org.gradle.api.Project


object BuildTypeName {
    const val debug = "debug"
    const val release = "release"
    const val staging = "staging"
}

object BuildConfigField {

    object BaseUrl {

        object Key {
            const val baseUrl = "BASE_URL"
        }

        object Value {
            const val dev = "https://xkcd.com/"
            const val production = "https://xkcd.com/"
        }
    }
}

fun Project.setupBuildTypesBlock(isApplication: Boolean) {
    android.run {

        buildTypes {

            getByName(BuildTypeName.debug) {

                addConfigField(
                    key = BuildConfigField.BaseUrl.Key.baseUrl,
                    value = BuildConfigField.BaseUrl.Value.dev.addDoubleQuotation()
                )

                isDebuggable = true

                if (isApplication) {

                    applicationIdSuffix = ".${BuildTypeName.debug}"

                    // Proguard
                    isMinifyEnabled = false
                    isShrinkResources = false
                    isUseProguard = false
                }

            }

            getByName(BuildTypeName.release) {

                addConfigField(
                    key = BuildConfigField.BaseUrl.Key.baseUrl,
                    value = BuildConfigField.BaseUrl.Value.production.addDoubleQuotation()
                )

                isDebuggable = false

                if (isApplication) {
                    // Proguard
                    isShrinkResources = true
                    isMinifyEnabled = true
                    isUseProguard = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android.txt"),
                        "proguard-rules.pro"
                    )
                }

            }

            create(BuildTypeName.staging) {

                addConfigField(
                    key = BuildConfigField.BaseUrl.Key.baseUrl,
                    value = BuildConfigField.BaseUrl.Value.dev.addDoubleQuotation()
                )

                isDebuggable = true

                if (isApplication) {

                    applicationIdSuffix = ".${BuildTypeName.staging}"

                    // Proguard
                    isMinifyEnabled = false
                    isShrinkResources = false
                    isUseProguard = false
                }
            }
        }
    }
}

