package io.shortcut.android.xkcd.comics.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.shortcut.android.xkcd.comics.database.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(comic: FavoriteEntity): Long

    @Query("SELECT * FROM favorite")
    fun getFavorites(): PagingSource<Int, FavoriteEntity>

    @Query("SELECT * FROM favorite WHERE comicNumber = :comicNumber")
    fun getByNumber(comicNumber: Int): FavoriteEntity

    @Query("DELETE FROM favorite WHERE comicNumber = :comicNumber")
    fun delete(comicNumber: Int): Int

}