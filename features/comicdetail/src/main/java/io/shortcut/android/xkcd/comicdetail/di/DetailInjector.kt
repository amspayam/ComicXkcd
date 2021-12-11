package io.shortcut.android.xkcd.comicdetail.di

import io.shortcut.android.xkcd.comicdetail.di.modules.detailDataModule
import io.shortcut.android.xkcd.comicdetail.di.modules.detailPresentationModule
import io.shortcut.android.xkcd.comicdetail.di.modules.detailUseCaseModule


object DetailInjector {
    fun provideDependencies() = listOf(
        detailPresentationModule,
        detailUseCaseModule,
        detailDataModule
    )
}