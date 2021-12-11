package io.shortcut.android.xkcd.comics.finder.domain

import io.shortcut.android.xkcd.comics.finder.data.entity.ComicEntity
import io.shortcut.android.xkcd.comics.repository.ResultModel

interface ComicFinderRepository {
    suspend fun getLastComic(): ResultModel<ComicEntity>
    suspend fun getComicByNumber(number: Int): ResultModel<ComicEntity>
}