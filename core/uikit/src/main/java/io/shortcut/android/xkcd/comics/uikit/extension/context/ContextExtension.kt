package io.shortcut.android.xkcd.comics.uikit.extension.context

import android.content.Context

fun Context.getVersionApplication(): String? {
    return this.packageManager.getPackageInfo(
        this.packageName,
        0
    ).versionName
}