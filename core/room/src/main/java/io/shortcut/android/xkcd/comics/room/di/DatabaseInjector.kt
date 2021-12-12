package io.shortcut.android.xkcd.comics.room.di

import androidx.room.Room
import io.shortcut.android.xkcd.comics.room.db.ComicsDatabase
import org.koin.dsl.module

object DatabaseInjector {
    fun provideDependencies() = module {
        single {
            Room.databaseBuilder(
                get(),
                ComicsDatabase::class.java,
                "comics_db"
            ).build()
        }

        factory { get<ComicsDatabase>().favoriteDao }
    }
}