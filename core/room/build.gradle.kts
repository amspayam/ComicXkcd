plugins {
    id("shortcut-library-plugin")
}

dependencies {

    // Modules
    implementation(project(":core:base"))

    koinDG()
    coroutinesDG()
    retrofitAndGsonDG()
    chuckDG()
    roomDG()

    implementation(Libraries.paging)

}