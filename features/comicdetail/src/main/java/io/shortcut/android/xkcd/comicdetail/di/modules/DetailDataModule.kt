package io.shortcut.android.xkcd.comicdetail.di.modules

import io.shortcut.android.xkcd.comicdetail.data.DetailRepositoryImpl
import io.shortcut.android.xkcd.comicdetail.data.api.ExplanationApiService
import io.shortcut.android.xkcd.comicdetail.data.network.ExplanationRemoteDataSource
import io.shortcut.android.xkcd.comicdetail.data.network.ExplanationRemoteDataSourceImpl
import io.shortcut.android.xkcd.comicdetail.domain.DetailRepository
import io.shortcut.android.xkcd.comics.repository.network.NetworkManager
import org.koin.dsl.module

val detailDataModule = module {
    factory<DetailRepository> { DetailRepositoryImpl(get()) }
    factory<ExplanationRemoteDataSource> { ExplanationRemoteDataSourceImpl(get()) }

    factory { provideApiService(get()) }
}

private fun provideApiService(networkManager: NetworkManager): ExplanationApiService {
    return networkManager.create(
        baseUrl = "https://www.explainxkcd.com/wiki/",
        ExplanationApiService::class.java
    )
}
