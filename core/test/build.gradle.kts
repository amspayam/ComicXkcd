plugins {
    id("shortcut-library-plugin")
}

dependencies {
    testDG()
    testImplementation(Libraries.gson)
}

// Test Artifacts for use test utils in other modules
configurations {
    create("testArtifacts")
}

tasks.register("testJar", Jar::class.java) {
    archiveBaseName.set(project.name)
    from("$buildDir/tmp/kotlin-classes/UATDebugUnitTest")
}

artifacts {
    add("testArtifacts", tasks["testJar"])
}