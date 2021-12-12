package io.shortcut.android.xkcd.comics.favorite.data.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.shortcut.android.xkcd.comics.room.dao.FavoriteDao
import io.shortcut.android.xkcd.comics.room.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow


class FavoriteLocalDataSourceImpl(
    private val favoriteDao: FavoriteDao
) : FavoriteLocalDataSource {

    override suspend fun insert(favoriteEntity: FavoriteEntity): Long {
        return favoriteDao.insert(favoriteEntity)
    }

    override suspend fun delete(number: Int): Int {
        return favoriteDao.delete(comicNumber = number)
    }

    override suspend fun getByNumber(number: Int): FavoriteEntity {
        return favoriteDao.getByNumber(comicNumber = number)
    }

    override suspend fun getByPagination(): Flow<PagingData<FavoriteEntity>> {
        val pager = Pager(
            config = PagingConfig(
                prefetchDistance = 10,
                pageSize = 20,
                enablePlaceholders = true
            )
        ) {
            favoriteDao.getFavorites()
        }
        return pager.flow
    }


}