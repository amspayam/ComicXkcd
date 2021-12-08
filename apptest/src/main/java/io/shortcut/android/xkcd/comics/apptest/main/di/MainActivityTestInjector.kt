package io.shortcut.android.xkcd.comics.apptest.main.di

import io.shortcut.android.xkcd.comics.apptest.main.di.modules.mainActivityTestPresentationModule


object MainActivityTestInjector {
    fun provideDependencies() = listOf(
        mainActivityTestPresentationModule
    )
}