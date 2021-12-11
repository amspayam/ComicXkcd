package io.shortcut.android.xkcd.comics.finder.di.modules

import io.shortcut.android.xkcd.comics.finder.domain.usecase.ComicByNumberUseCase
import io.shortcut.android.xkcd.comics.finder.domain.usecase.LastComicUseCase
import org.koin.dsl.module

val comicsUseCaseModule = module {
    factory { LastComicUseCase(get()) }
    factory { ComicByNumberUseCase(get()) }
}