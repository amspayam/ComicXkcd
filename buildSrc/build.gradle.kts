import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

gradlePlugin {
    plugins {

        // Application Plugin
        register("shortcut-application-plugin") {
            id = "shortcut-application-plugin"
            implementationClass = "io.shortcut.android.xkcd.comics.plugins.ShortcutApplicationPlugin"
        }

        // Library Plugin
        register("shortcut-library-plugin") {
            id = "shortcut-library-plugin"
            implementationClass = "io.shortcut.android.xkcd.comics.plugins.ShortcutLibraryPlugin"
        }
    }
}

buildscript {

    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { setUrl("https://jitpack.io") }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.21.0")
    }
}

repositories {
    google()
    mavenCentral()
    jcenter()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.6.0"
}

dependencies {
    compileOnly(gradleKotlinDsl())
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:4.2.2")
    implementation(kotlin("gradle-plugin", "1.6.0"))
    implementation(kotlin("gradle-plugin-api", "1.6.0"))
    implementation(kotlin("android-extensions"))

}