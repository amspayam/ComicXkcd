object Versions {

    // <editor-fold desc="Tools">
    const val androidGradle = "4.2.2"
    const val googleServicesGradle = "4.3.8"
    const val kotlin = "1.5.21"
    const val gradleVersions = "0.21.0"
    const val tools_navigationSafeArgsGradle = "2.3.5"
    const val firebasePerformance = "1.3.1"
    // </editor-fold>

    // <editor-fold desc="Google">

    // Material
    const val material = "1.2.1"

    // Multidex
    const val androidx_multidex = "2.0.1"

    // Annotation
    const val androidx_annotation = "1.1.0"

    // KTX
    const val androidx_core_ktx = "1.2.0"

    // Views
    const val androidx_recyclerview = "1.1.0"
    const val androidx_cardview = "1.0.0"
    const val androidx_appcompat = "1.2.0"
    const val androidx_legacySupportV4 = "1.0.0"
    const val androidx_constraintLayout = "2.0.4"

    // Preferences
    const val androidx_preferences = "1.1.1"

    // Lifecycle
    const val androidx_lifecycle_viewmodel = "2.2.0"
    const val androidx_lifecycle_livedata = "2.2.0"

    // Navigation
    const val androidx_navigation_runtime = "2.4.0-alpha05"
    const val androidx_navigation_fragment = "2.4.0-alpha05"
    const val androidx_navigation_ui = "2.4.0-alpha05"

    // </editor-fold>

    // <editor-fold desc="FireBase">
    const val firebase_core = "17.3.0"
    const val firebase_messaging = "20.1.4"
    // </editor-fold>

    // <editor-fold desc="Crashlytics">
    const val crashlytics = "2.10.0"
    const val firebasePer = "19.0.5"
    // </editor-fold>

    //<editor-fold desc="Koin">
    const val koin_android = "2.1.3"
    const val koin_core = "2.1.3"
    const val koin_androidx_scope = "2.1.3"
    const val koin_androidx_viewmodel = "2.1.3"
    const val koin_android_architecture = "0.9.3"
    // </editor-fold>

    //<editor-fold desc="Coroutines">
    const val coroutines_core = "1.5.2-native-mt"
    const val coroutines_android = "1.5.2-native-mt"
    // </editor-fold>

    // <editor-fold desc="Retrofit and Gson">
    const val retrofit = "2.6.2"
    const val retrofit_converterGson = "2.6.2"
    const val retrofit_converterScalars = "2.1.0"
    const val retrofit_adapterRxJava2 = "2.6.2"
    const val okhttp3_loggingInterceptor = "3.12.0"
    const val okLog = "2.3.0"
    const val gson = "2.8.5"
    // </editor-fold>

    // <editor-fold desc="Glide">
    const val glide = "4.9.0"
    const val glideCompiler = "4.9.0"
    // </editor-fold>

    // <editor-fold desc="Rx">
    const val rxjava = "2.2.2"
    const val rxandroid = "2.1.0"
    // </editor-fold>

    // <editor-fold desc="Logger">
    const val logger = "2.2.0"
    // </editor-fold>

    // <editor-fold desc="Chuck">
    const val chuck = "1.1.0"
    // </editor-fold>

    // <editor-fold desc="Test">
    const val junit = "4.12"
    const val androidxTest = "1.3.0"
    const val testCore = "2.1.0"

    // Espresso
    const val androidxTestEspresso = "3.1.0"

    // Mockk
    const val mockk = "1.10.2"

    // Coroutines
    const val testCoroutines = "1.4.2"

    //</editor-fold>

    // <editor-fold desc="Room">
    const val room = "2.3.0"
    //</editor-fold>

    // <editor-fold desc="Paging">
    const val paging = "1.0.0-alpha4-1"
    //</editor-fold>
}

object Libraries {

    // <editor-fold desc="Tools">

    const val tools_androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val tools_googleServicesGradle =
        "com.google.gms:google-services:${Versions.googleServicesGradle}"
    const val tools_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val tools_gradleVersions =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersions}"
    const val tools_navigationSafeArgsGradle =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.tools_navigationSafeArgsGradle}"
    const val tools_firebasePerformance =
        "com.google.firebase:perf-plugin:${Versions.firebasePerformance}"
    // </editor-fold>

    // <editor-fold desc="Kotlin">
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    // </editor-fold>

    // <editor-fold desc="Google">

    // Material
    const val material = "com.google.android.material:material:${Versions.material}"

    // MultiDex
    const val androidx_multiDex = "androidx.multidex:multidex:${Versions.androidx_multidex}"

    // Annotation
    const val androidx_annotation = "androidx.annotation:annotation:${Versions.androidx_annotation}"

    // KTX
    const val androidx_core_ktx = "androidx.core:core-ktx:${Versions.androidx_core_ktx}"

    // Views
    const val androidx_recyclerView =
        "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"
    const val androidx_cardView = "androidx.cardview:cardview:${Versions.androidx_cardview}"
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val androidx_legacySupportV4 =
        "androidx.legacy:legacy-support-v4:${Versions.androidx_legacySupportV4}"
    const val androidx_constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintLayout}"

    //Preferences
    const val androidx_preferences =
        "androidx.preference:preference-ktx:${Versions.androidx_preferences}"

    // Lifecycle
    const val androidx_lifecycle_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_lifecycle_viewmodel}"
    const val androidx_lifecycle_livedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidx_lifecycle_livedata}"

    // Navigation
    const val androidx_navigation_runtime =
        "androidx.navigation:navigation-runtime-ktx:${Versions.androidx_navigation_runtime}"
    const val androidx_navigation_fragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation_fragment}"
    const val androidx_navigation_ui =
        "androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation_ui}"
    // </editor-fold>

    // <editor-fold desc="Firebase">
    const val firebase_core = "com.google.firebase:firebase-core:${Versions.firebase_core}"
    const val firebase_messaging =
        "com.google.firebase:firebase-messaging:${Versions.firebase_messaging}"
    // </editor-fold>

    // <editor-fold desc="Crashlytics">
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"
    const val firebasePer = "com.google.firebase:firebase-perf:${Versions.firebasePer}"
    // </editor-fold>

    //<editor-fold desc="Koin">
    const val koin_android = "org.koin:koin-android:${Versions.koin_android}"
    const val koin_core = "org.koin:koin-core:${Versions.koin_core}"
    const val koin_androidx_scope = "org.koin:koin-androidx-scope:${Versions.koin_androidx_scope}"
    const val koin_androidx_viewmodel =
        "org.koin:koin-androidx-viewmodel:${Versions.koin_androidx_viewmodel}"
    const val koin_android_architecture =
        "org.koin:koin-android-architecture:${Versions.koin_android_architecture}"

    // </editor-fold>

    //<editor-fold desc="Coroutines">
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_core}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"
    // </editor-fold>

    // <editor-fold desc="Retrofit and Gson">
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converterGson =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit_converterGson}"
    const val retrofit_converterScalars =
        "com.squareup.retrofit2:converter-scalars:${Versions.retrofit_converterScalars}"
    const val retrofit_adapterRxJava2 =
        "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit_adapterRxJava2}"
    const val okhttp3_loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3_loggingInterceptor}"
    const val okLog = "com.github.simonpercic:oklog3:${Versions.okLog}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    // </editor-fold>

    // <editor-fold desc="Glide">
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
    // </editor-fold>

    // <editor-fold desc="Logger">
    const val logger = "com.orhanobut:logger:${Versions.logger}"
    // </editor-fold>

    // <editor-fold desc="Chuck">
    const val chuckDebug = "com.readystatesoftware.chuck:library:${Versions.chuck}"
    const val chuckRelease = "com.readystatesoftware.chuck:library-no-op:${Versions.chuck}"
    // </editor-fold>

    // <editor-fold desc="Test">
    const val testJunit4 = "junit:junit:${Versions.junit}"
    const val testAndroidxCore = "androidx.test:core:${Versions.androidxTest}"
    const val testAndroidxRules = "androidx.test:rules:${Versions.androidxTest}"
    const val testAndroidxRunner = "androidx.test:runner:${Versions.androidxTest}"
    const val testCore = "androidx.arch.core:core-testing:${Versions.testCore}"

    // Espresso
    const val testAndroidxEspressoCore =
        "androidx.test.espresso:espresso-core:${Versions.androidxTestEspresso}"

    // Mockk
    const val mockk = "io.mockk:mockk:${Versions.mockk}"

    // Coroutines
    const val testCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_android}"
    // </editor-fold>

    // <editor-fold desc="Room">
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    // </editor-fold>

    // <editor-fold desc="Paging">
    const val paging = "android.arch.paging:runtime:${Versions.paging}"
    // </editor-fold>


}
