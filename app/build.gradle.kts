plugins {
    id("shortcut-application-plugin")
}

dependencies {

    // Modules
    implementation(project(":core:base"))
    implementation(project(":core:uikit"))
    implementation(project(":core:repository"))
    implementation(project(":core:navigation"))
    implementation(project(":core:database"))


    // Features
    implementation(project(":features:main"))
    implementation(project(":features:comics"))
    implementation(project(":features:comicdetail"))
    implementation(project(":features:favorite"))

    // Kotlin stdlib
    implementation(Libraries.kotlin_stdlib)

    // MultiDex
    implementation(Libraries.androidx_multiDex)

    // KTX
    implementation(Libraries.androidx_core_ktx)

    koinDG()
    coroutinesDG()
    retrofitAndGsonDG()
    navigationDG()
    androidXViewDG()

}