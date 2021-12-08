plugins {
    id("shortcut-library-plugin")
}

dependencies {

    koinDG()
    coroutinesDG()
    retrofitAndGsonDG()
    navigationDG()
    chuckDG()

    // Logger
    implementation(Libraries.logger)

}