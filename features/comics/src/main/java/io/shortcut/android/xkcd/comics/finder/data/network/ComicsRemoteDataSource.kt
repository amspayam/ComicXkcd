package io.shortcut.android.xkcd.comics.finder.data.network

import io.shortcut.android.xkcd.comics.finder.data.entity.ComicEntity
import io.shortcut.android.xkcd.comics.repository.ResultModel

interface ComicsRemoteDataSource {
    suspend fun getLastComics(): ResultModel<ComicEntity>
    suspend fun getComicByNumber(number: Int): ResultModel<ComicEntity>
}