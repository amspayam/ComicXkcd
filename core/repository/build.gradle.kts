plugins {
    id("shortcut-library-plugin")
}

dependencies {

    // Modules
    implementation(project(":core:base"))
    implementation(project(":core:cachemanager"))

    koinDG()
    coroutinesDG()
    retrofitAndGsonDG()
    chuckDG()

}