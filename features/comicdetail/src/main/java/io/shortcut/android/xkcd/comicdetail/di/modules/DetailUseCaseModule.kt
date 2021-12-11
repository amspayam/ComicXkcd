package io.shortcut.android.xkcd.comicdetail.di.modules

import io.shortcut.android.xkcd.comicdetail.domain.usecase.ExplanationUseCase
import org.koin.dsl.module

val detailUseCaseModule = module {
    factory { ExplanationUseCase(get()) }
}