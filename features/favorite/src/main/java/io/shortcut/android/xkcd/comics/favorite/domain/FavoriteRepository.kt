package io.shortcut.android.xkcd.comics.favorite.domain

import androidx.paging.PagingData
import io.shortcut.android.xkcd.comics.database.entity.FavoriteEntity
import io.shortcut.android.xkcd.comics.repository.ResultModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun getFavoriteList(
        prefetchDistance: Int,
        pageSize: Int,
        enablePlaceholders: Boolean
    ): ResultModel<Flow<PagingData<FavoriteEntity>>>

    suspend fun getFavoriteByNumber(comicNumber: Int): ResultModel<FavoriteEntity>
    suspend fun addFavorite(comic: FavoriteEntity): ResultModel<Long>
    suspend fun deleteFavorite(comicNumber: Int): ResultModel<Int>
}