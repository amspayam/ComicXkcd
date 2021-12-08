package io.shortcut.android.xkcd.comics.main.splash.di

import io.shortcut.android.xkcd.comics.main.splash.di.modules.SplashPresentationModule

object SplashInjector {
    fun provideDependencies() = listOf(
        SplashPresentationModule
    )
}