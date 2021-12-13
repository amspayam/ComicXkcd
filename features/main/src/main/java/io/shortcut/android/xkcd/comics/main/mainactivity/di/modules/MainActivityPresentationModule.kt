package io.shortcut.android.xkcd.comics.main.mainactivity.di.modules

import io.shortcut.android.xkcd.comics.main.mainactivity.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val MainActivityPresentationModule = module {
    viewModel { MainViewModel() }
}