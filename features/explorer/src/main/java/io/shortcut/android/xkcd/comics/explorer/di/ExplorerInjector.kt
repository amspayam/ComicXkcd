package io.shortcut.android.xkcd.comics.explorer.di

import io.shortcut.android.xkcd.comics.explorer.di.modules.explorerDataModule
import io.shortcut.android.xkcd.comics.explorer.di.modules.explorerPresentationModule
import io.shortcut.android.xkcd.comics.explorer.di.modules.explorerUseCaseModule


object ExplorerInjector {
    fun provideDependencies() = listOf(
        explorerPresentationModule,
        explorerUseCaseModule,
        explorerDataModule
    )
}