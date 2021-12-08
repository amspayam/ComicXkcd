plugins {
    id("shortcut-library-plugin")
}

dependencies {
    androidXViewDG()
    navigationDG()
    coroutinesDG()
    implementation(Libraries.glide)
    implementation(Libraries.skeleton)
}
