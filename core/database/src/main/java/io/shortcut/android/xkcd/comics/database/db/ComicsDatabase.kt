package io.shortcut.android.xkcd.comics.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.shortcut.android.xkcd.comics.database.dao.FavoriteDao
import io.shortcut.android.xkcd.comics.database.entity.FavoriteEntity


@Database(
    entities = [
        FavoriteEntity::class
    ], version = 1, exportSchema = false
)
abstract class ComicsDatabase : RoomDatabase() {

    abstract val favoriteDao: FavoriteDao

    companion object {
        private const val DATABASE_NAME = "comics_xkcd_db"

        @Volatile
        private var instance: ComicsDatabase? = null

        fun getInstance(context: Context): ComicsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ComicsDatabase {
            return Room.databaseBuilder(context, ComicsDatabase::class.java, DATABASE_NAME)
                .build()
        }

    }
}