package io.shortcut.android.xkcd.comics.finder.di.modules

import io.shortcut.android.xkcd.comics.finder.data.ComicFinderRepositoryImpl
import io.shortcut.android.xkcd.comics.finder.data.api.ComicsApiService
import io.shortcut.android.xkcd.comics.finder.data.network.ComicsRemoteDataSource
import io.shortcut.android.xkcd.comics.finder.data.network.ComicsRemoteDataSourceImpl
import io.shortcut.android.xkcd.comics.finder.domain.ComicFinderRepository
import io.shortcut.android.xkcd.comics.repository.network.NetworkManager
import org.koin.dsl.module

val comicsDataModule = module {
    factory<ComicFinderRepository> { ComicFinderRepositoryImpl(get()) }
    factory<ComicsRemoteDataSource> { ComicsRemoteDataSourceImpl(get()) }

    factory { provideApiService(get()) }
}

private fun provideApiService(networkManager: NetworkManager): ComicsApiService {
    return networkManager.create(ComicsApiService::class.java)
}
