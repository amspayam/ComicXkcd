package io.shortcut.android.xkcd.comics.main.splash.di.modules

import io.shortcut.android.xkcd.comics.main.splash.presenter.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val SplashPresentationModule = module {
    viewModel { SplashViewModel() }
}