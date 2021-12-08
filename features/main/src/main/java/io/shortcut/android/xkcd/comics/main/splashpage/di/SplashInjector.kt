package io.shortcut.android.xkcd.comics.main.splashpage.di

import io.shortcut.android.xkcd.comics.main.splashpage.di.modules.SplashPresentationModule

object SplashInjector {
    fun provideDependencies() = listOf(
        SplashPresentationModule
    )
}