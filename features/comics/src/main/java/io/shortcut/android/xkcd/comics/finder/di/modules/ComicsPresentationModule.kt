package io.shortcut.android.xkcd.comics.finder.di.modules

import io.shortcut.android.xkcd.comics.finder.presenter.ComicFinderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val comicsPresentationModule = module {
    viewModel { ComicFinderViewModel(get(), get()) }
}