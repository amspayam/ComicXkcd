package io.shortcut.android.xkcd.comics.explorer.di.modules

import io.shortcut.android.xkcd.comics.explorer.domain.usecase.ComicByNumberUseCase
import io.shortcut.android.xkcd.comics.explorer.domain.usecase.LastComicUseCase
import org.koin.dsl.module

val explorerUseCaseModule = module {
    factory { LastComicUseCase(get()) }
    factory { ComicByNumberUseCase(get()) }
}