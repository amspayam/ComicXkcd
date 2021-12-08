package io.shortcut.android.xkcd.comics.main.mainactivity.di

import io.shortcut.android.xkcd.comics.main.mainactivity.di.modules.MainActivityPresentationModule


object MainActivityInjector {
    fun provideDependencies() = listOf(
        MainActivityPresentationModule
    )
}