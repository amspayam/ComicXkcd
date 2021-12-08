package io.shortcut.android.xkcd.comics.config

object ApplicationId {

    const val applicationId = "io.shortcut.comics"

    val versionCode = AppVersion.computeVersionCode()
    val versionName = AppVersion.computeVersionName()

}

object AppVersion {

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0
    private const val versionBuild = 0

    fun computeVersionCode(): Int {
        // Major + Minor + Patch + CI(Jenkins) build number (where available)
        return (versionMajor * 10000000) +
                (versionMinor * 100000) +
                (versionPatch * 1000) +
                Integer.valueOf(versionBuild)
    }

    fun computeVersionName(): String {
        // Major + Minor + Patch + Build
        return "$versionMajor.$versionMinor.$versionPatch.$versionBuild"
    }
}


