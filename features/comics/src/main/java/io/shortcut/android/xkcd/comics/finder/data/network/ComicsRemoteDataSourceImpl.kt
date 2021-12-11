package io.shortcut.android.xkcd.comics.finder.data.network

import io.shortcut.android.xkcd.comics.finder.data.api.ComicsApiService
import io.shortcut.android.xkcd.comics.finder.data.entity.ComicEntity
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.awaitResult

class ComicsRemoteDataSourceImpl(
    private val apiService: ComicsApiService
) : ComicsRemoteDataSource {

    override suspend fun getLastComics(): ResultModel<ComicEntity> {
        return apiService.getLastComics().awaitResult { it }
    }

    override suspend fun getComicByNumber(number: Int): ResultModel<ComicEntity> {
        return apiService.getComicByNumber(number = number).awaitResult { it }
    }

}