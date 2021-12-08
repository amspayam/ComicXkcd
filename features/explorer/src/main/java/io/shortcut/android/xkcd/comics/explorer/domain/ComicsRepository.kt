package io.shortcut.android.xkcd.comics.explorer.domain

import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.explorer.data.entity.ComicEntity

interface ComicsRepository {
    suspend fun getLastComic(): ResultModel<ComicEntity>
    suspend fun getComicByNumber(number: Int): ResultModel<ComicEntity>
}