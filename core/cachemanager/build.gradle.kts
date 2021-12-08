plugins {
    id("shortcut-library-plugin")
}

dependencies {

    // Modules
    implementation(project(":core:base"))

    koinDG()
    rxDG()
    coroutinesDG()
    retrofitAndGsonDG()
    chuckDG()

}