plugins {
    id("shortcut-application-plugin")
}

dependencies {

    // Modules
    implementation(project(":core:base"))
    implementation(project(":core:uikit"))
    implementation(project(":core:cachemanager"))
    implementation(project(":core:repository"))
    implementation(project(":core:navigation"))


    // Features
    implementation(project(":features:main"))
//    implementation(project(":features:shared"))
//    implementation(project(":features:explorer"))
//    implementation(project(":features:random"))
//    implementation(project(":features:comicdetail"))
//    implementation(project(":features:favorite"))
//    implementation(project(":features:search"))

    // Kotlin stdlib
    implementation(Libraries.kotlin_stdlib)

    // MultiDex
    implementation(Libraries.androidx_multiDex)

    // KTX
    implementation(Libraries.androidx_core_ktx)

    koinDG()
    rxDG()
    coroutinesDG()
    retrofitAndGsonDG()
    navigationDG()
    androidXViewDG()

}