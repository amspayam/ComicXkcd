package io.shortcut.android.xkcd.comics.config.blocks

import io.shortcut.android.xkcd.comics.extenstion.addConfigField
import io.shortcut.android.xkcd.comics.extenstion.addDoubleQuotation
import io.shortcut.android.xkcd.comics.extenstion.android
import org.gradle.api.Project


object FlavorName {
    const val googlePlay = "googlePlay"
    const val UAT = "UAT"
}

object FlavorBuildConfigField {

    object AppStoreName {

        object Key {
            const val appStoreName = "APP_STORE_NAME"
        }

        object Value {
            const val googlePlay = "GooglePlay"
            const val UAT = "UAT"
        }
    }

    object AppStoreUrl {

        object Key {
            const val appStoreUrl = "APP_STORE_URL"
        }

        object Value {
            const val googlePlay =
                "https://play.google.com/store/apps/details?id=io.shortcut.android.xkcd.comics"
            const val UAT = "https://shortcut.io"
        }
    }

}

fun Project.setupFlavorBlock(isApplication: Boolean) {
    android.run {
        productFlavors {

            create(FlavorName.googlePlay) {
                addConfigField(
                    key = FlavorBuildConfigField.AppStoreName.Key.appStoreName,
                    value = FlavorBuildConfigField.AppStoreName.Value.googlePlay.addDoubleQuotation()
                )
                addConfigField(
                    key = FlavorBuildConfigField.AppStoreUrl.Key.appStoreUrl,
                    value = FlavorBuildConfigField.AppStoreUrl.Value.googlePlay.addDoubleQuotation()
                )
            }

            create(FlavorName.UAT) {
                addConfigField(
                    key = FlavorBuildConfigField.AppStoreName.Key.appStoreName,
                    value = FlavorBuildConfigField.AppStoreName.Value.UAT.addDoubleQuotation()
                )
                addConfigField(
                    key = FlavorBuildConfigField.AppStoreUrl.Key.appStoreUrl,
                    value = FlavorBuildConfigField.AppStoreUrl.Value.UAT.addDoubleQuotation()
                )
            }
        }
    }
}