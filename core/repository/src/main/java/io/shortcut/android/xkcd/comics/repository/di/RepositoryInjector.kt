package io.shortcut.android.xkcd.comics.repository.di


import io.shortcut.android.xkcd.comics.repository.network.NetworkManager
import org.koin.dsl.module

object RepositoryInjector {

    fun provideDependencies() = module {
        single { NetworkManager(get()) }
    }
}