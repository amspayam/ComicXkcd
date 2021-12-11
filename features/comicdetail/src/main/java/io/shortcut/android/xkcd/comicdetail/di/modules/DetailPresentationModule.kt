package io.shortcut.android.xkcd.comicdetail.di.modules

import io.shortcut.android.xkcd.comicdetail.presenter.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailPresentationModule = module {
    viewModel { DetailViewModel(get()) }
}