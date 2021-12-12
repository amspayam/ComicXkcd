package io.shortcut.android.xkcd.comics.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite"
)
data class FavoriteEntity(
    @PrimaryKey
    var comicNumber: Int,
    @ColumnInfo(name = "comicName")
    var comicName: String,
    @ColumnInfo(name = "comicDescription")
    var comicDescription: String,
    @ColumnInfo(name = "comicImageLink")
    var comicImageLink: String
)