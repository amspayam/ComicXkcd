package io.shortcut.android.xkcd.comics.favorite.di.modules

import io.shortcut.android.xkcd.comics.favorite.data.FavoriteRepositoryImpl
import io.shortcut.android.xkcd.comics.favorite.data.local.FavoriteLocalDataSource
import io.shortcut.android.xkcd.comics.favorite.data.local.FavoriteLocalDataSourceImpl
import io.shortcut.android.xkcd.comics.favorite.domain.FavoriteRepository
import org.koin.dsl.module


val favoriteDataModule = module {
    factory<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
    factory<FavoriteLocalDataSource> { FavoriteLocalDataSourceImpl(get()) }
}

