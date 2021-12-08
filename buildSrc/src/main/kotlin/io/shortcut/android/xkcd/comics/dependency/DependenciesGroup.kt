import io.shortcut.android.xkcd.comics.extenstion.group
import org.gradle.kotlin.dsl.DependencyHandlerScope


// DP = Dependency Group

fun DependencyHandlerScope.androidXViewDG() {
    group(
        Libraries.material,
        Libraries.androidx_recyclerView,
        Libraries.androidx_cardView,
        Libraries.androidx_preferences,
        Libraries.androidx_appcompat,
        Libraries.androidx_legacySupportV4,
        Libraries.androidx_constraintLayout
    )
}

fun DependencyHandlerScope.retrofitAndGsonDG() {
    group(
        Libraries.retrofit,
        Libraries.retrofit_converterGson,
        Libraries.retrofit_converterScalars,
        Libraries.retrofit_adapterRxJava2,
        Libraries.okhttp3_loggingInterceptor,
        Libraries.okLog,
        Libraries.gson
    )
}

fun DependencyHandlerScope.rxDG() {
    group(
        Libraries.rxJava,
        Libraries.rxAndroid
    )
}

fun DependencyHandlerScope.coroutinesDG() {
    group(
        Libraries.coroutines_core,
        Libraries.coroutines_android
    )
}

fun DependencyHandlerScope.koinDG() {
    group(
        Libraries.koin_android,
        Libraries.koin_core,
        Libraries.koin_androidx_scope,
        Libraries.koin_androidx_viewmodel,
        Libraries.koin_android_architecture
    )
}

fun DependencyHandlerScope.navigationDG() {
    group(
        Libraries.androidx_navigation_runtime,
        Libraries.androidx_navigation_fragment,
        Libraries.androidx_navigation_ui
    )
}

fun DependencyHandlerScope.lifeCycleDG() {
    group(
        Libraries.androidx_lifecycle_viewmodel,
        Libraries.androidx_lifecycle_livedata
    )
}

fun DependencyHandlerScope.glideDG() {
    "implementation"(Libraries.glide)
    "annotationProcessor"(Libraries.glideCompiler)
}

fun DependencyHandlerScope.roomDG() {
    "implementation"(Libraries.room_ktx)
    "kapt"(Libraries.room_compiler)
}

fun DependencyHandlerScope.chuckDG() {
    "debugImplementation"(Libraries.chuckDebug)
    "releaseImplementation"(Libraries.chuckRelease)
}


fun DependencyHandlerScope.testDG() {
    "testImplementation"(Libraries.testJunit4)
    "testImplementation"(Libraries.testAndroidxCore)
    "androidTestImplementation"(Libraries.testAndroidxCore)
    "testImplementation"(Libraries.testAndroidxRules)
    "testImplementation"(Libraries.testAndroidxRunner)
    "testImplementation"(Libraries.testCore)

    // Espresso
    "androidTestImplementation"(Libraries.testAndroidxEspressoCore)

    // Mockk
    "testImplementation"(Libraries.mockk)

    // Coroutines
    "testImplementation"(Libraries.testCoroutines)

}


fun DependencyHandlerScope.baseModuleDG() {
    /*Ktx*/
    Libraries.androidx_core_ktx
    /*Core*/
    koinDG()
    rxDG()
    coroutinesDG()
    retrofitAndGsonDG()
    navigationDG()
    lifeCycleDG()
    chuckDG()
    /*UI Kit*/
    androidXViewDG()
    navigationDG()

    glideDG()
}