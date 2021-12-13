plugins {
    id("shortcut-library-plugin")
}

dependencies {

    // Modules
    testImplementation(project(":core:test", "testArtifacts"))
    implementation(project(":core:base"))
    implementation(project(":core:uikit"))
    implementation(project(":core:repository"))
    implementation(project(":core:navigation"))
    implementation(project(":core:database"))

    // Features

    testDG()
    baseModuleDG()
    implementation(Libraries.paging)
}