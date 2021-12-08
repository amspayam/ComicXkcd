package io.shortcut.android.xkcd.comics.explorer.di.modules

import io.shortcut.android.xkcd.comics.explorer.presenter.ExplorerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val explorerPresentationModule = module {
    viewModel { ExplorerViewModel(get()) }
}