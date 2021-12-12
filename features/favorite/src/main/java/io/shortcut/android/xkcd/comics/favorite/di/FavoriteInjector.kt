package io.shortcut.android.xkcd.comics.favorite.di

import io.shortcut.android.xkcd.comics.favorite.di.modules.favoriteDataModule
import io.shortcut.android.xkcd.comics.favorite.di.modules.favoritePresentationModule
import io.shortcut.android.xkcd.comics.favorite.di.modules.favoriteUseCaseModule

object FavoriteInjector {
    fun provideDependencies() = listOf(
        favoritePresentationModule,
        favoriteUseCaseModule,
        favoriteDataModule
    )
}