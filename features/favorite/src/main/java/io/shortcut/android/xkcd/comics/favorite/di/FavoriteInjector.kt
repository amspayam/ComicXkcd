package io.shortcut.android.xkcd.comics.favorite.di

import io.shortcut.android.xkcd.comics.favorite.di.modules.*

object FavoriteInjector {
    fun provideDependencies() = listOf(
        favoritePresentationModule,
        favoriteUseCaseModule,
        favoriteDataModule
    )
}