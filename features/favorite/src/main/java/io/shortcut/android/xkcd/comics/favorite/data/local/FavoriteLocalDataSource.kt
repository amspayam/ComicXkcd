package io.shortcut.android.xkcd.comics.favorite.data.local

import androidx.paging.PagingData
import io.shortcut.android.xkcd.comics.database.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow


interface FavoriteLocalDataSource {
    suspend fun insert(favoriteEntity: FavoriteEntity): Long
    suspend fun delete(number: Int): Int
    suspend fun getByNumber(number: Int): FavoriteEntity
    suspend fun getByPagination(
        prefetchDistance: Int,
        pageSize: Int,
        enablePlaceholders: Boolean
    ): Flow<PagingData<FavoriteEntity>>
}