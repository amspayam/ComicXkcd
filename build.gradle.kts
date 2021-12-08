buildscript {
    repositories {
        google()
        jcenter()
        maven { setUrl("https://jitpack.io") }
    }

    dependencies {
        classpath(Libraries.tools_kotlin)
        classpath(Libraries.tools_googleServicesGradle)
        classpath(Libraries.tools_gradleVersions)
        classpath(Libraries.tools_navigationSafeArgsGradle)
        classpath(Libraries.tools_firebasePerformance)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    }

}

subprojects {
    afterEvaluate {
        if (project.hasProperty("android")) {
            apply(plugin = "com.github.ben-manes.versions")
        }
    }
}
allprojects {
    repositories {
        google()
        jcenter()
        maven { setUrl("https://jitpack.io") }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}