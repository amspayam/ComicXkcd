package io.shortcut.android.xkcd.comics.finder.di

import io.shortcut.android.xkcd.comics.finder.di.modules.comicsDataModule
import io.shortcut.android.xkcd.comics.finder.di.modules.comicsPresentationModule
import io.shortcut.android.xkcd.comics.finder.di.modules.comicsUseCaseModule


object ComicsInjector {
    fun provideDependencies() = listOf(
        comicsPresentationModule,
        comicsUseCaseModule,
        comicsDataModule
    )
}