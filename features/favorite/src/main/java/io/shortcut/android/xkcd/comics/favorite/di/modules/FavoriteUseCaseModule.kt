package io.shortcut.android.xkcd.comics.favorite.di.modules

import io.shortcut.android.xkcd.comics.favorite.domain.usecase.AddFavoriteUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.DeleteFavoriteUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.FavoriteByNumberUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.FavoriteListUseCase
import org.koin.dsl.module

val favoriteUseCaseModule = module {
    factory { AddFavoriteUseCase(get()) }
    factory { DeleteFavoriteUseCase(get()) }
    factory { FavoriteByNumberUseCase(get()) }
    factory { FavoriteListUseCase(get()) }
}