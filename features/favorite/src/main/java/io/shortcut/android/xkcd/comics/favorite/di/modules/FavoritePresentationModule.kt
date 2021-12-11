package io.shortcut.android.xkcd.comics.favorite.di.modules

import io.shortcut.android.xkcd.comics.favorite.presenter.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritePresentationModule = module {
    viewModel { FavoriteViewModel() }
}