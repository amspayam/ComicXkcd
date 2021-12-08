plugins {
    id("shortcut-library-plugin")
}

dependencies {

    koinDG()
    rxDG()
    coroutinesDG()
    retrofitAndGsonDG()
    navigationDG()
    chuckDG()

    // Logger
    implementation(Libraries.logger)

}