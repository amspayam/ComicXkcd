package io.shortcut.android.xkcd.comics.favorite.data.local

import androidx.paging.PagingData
import io.shortcut.android.xkcd.comics.room.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow


interface FavoriteLocalDataSource {
    suspend fun insert(favoriteEntity: FavoriteEntity): Long
    suspend fun delete(number: Int): Int
    suspend fun getByNumber(number: Int): FavoriteEntity
    suspend fun getByPagination(): Flow<PagingData<FavoriteEntity>>
}