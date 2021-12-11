package io.shortcut.android.xkcd.comics.finder.data

import io.shortcut.android.xkcd.comics.finder.data.entity.ComicEntity
import io.shortcut.android.xkcd.comics.finder.data.network.ComicsRemoteDataSource
import io.shortcut.android.xkcd.comics.finder.domain.ComicFinderRepository
import io.shortcut.android.xkcd.comics.repository.ResultModel

class ComicFinderRepositoryImpl(private val dataSource: ComicsRemoteDataSource) : ComicFinderRepository {

    override suspend fun getLastComic(): ResultModel<ComicEntity> {
        return dataSource.getLastComics()
    }

    override suspend fun getComicByNumber(number: Int): ResultModel<ComicEntity> {
        return dataSource.getComicByNumber(number = number)
    }

}