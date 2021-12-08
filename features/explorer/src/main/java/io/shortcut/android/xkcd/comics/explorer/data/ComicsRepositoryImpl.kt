package io.shortcut.android.xkcd.comics.explorer.data

import io.shortcut.android.xkcd.comics.explorer.data.entity.ComicEntity
import io.shortcut.android.xkcd.comics.explorer.data.network.ComicsRemoteDataSource
import io.shortcut.android.xkcd.comics.explorer.domain.ComicsRepository
import io.shortcut.android.xkcd.comics.repository.ResultModel

class ComicsRepositoryImpl(private val dataSource: ComicsRemoteDataSource) : ComicsRepository {

    override suspend fun getLastComic(): ResultModel<ComicEntity> {
        return dataSource.getLastComics()
    }

    override suspend fun getComicByNumber(number: Int): ResultModel<ComicEntity> {
        return dataSource.getComicByNumber(number = number)
    }

}