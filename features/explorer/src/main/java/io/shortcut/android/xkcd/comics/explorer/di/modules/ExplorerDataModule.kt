package io.shortcut.android.xkcd.comics.explorer.di.modules

import io.shortcut.android.xkcd.comics.explorer.data.ComicsRepositoryImpl
import io.shortcut.android.xkcd.comics.explorer.data.api.ComicsApiService
import io.shortcut.android.xkcd.comics.explorer.data.network.ComicsRemoteDataSource
import io.shortcut.android.xkcd.comics.explorer.data.network.ComicsRemoteDataSourceImpl
import io.shortcut.android.xkcd.comics.explorer.domain.ComicsRepository
import io.shortcut.android.xkcd.comics.repository.network.NetworkManager
import org.koin.dsl.module

val explorerDataModule = module {
    factory<ComicsRepository> { ComicsRepositoryImpl(get()) }
    factory<ComicsRemoteDataSource> { ComicsRemoteDataSourceImpl(get()) }

    factory { provideApiService(get()) }
}

private fun provideApiService(networkManager: NetworkManager): ComicsApiService {
    return networkManager.create(ComicsApiService::class.java)
}
