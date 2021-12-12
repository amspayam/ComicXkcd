package io.shortcut.android.xkcd.comics.favorite.testdataprovider


import io.shortcut.android.xkcd.comics.room.entity.FavoriteEntity

class FavoriteDataProvider {

    companion object {

        fun getFavoriteEntityFilled(): FavoriteEntity {
            // it's same as getFavoriteFilled
            return FavoriteEntity(
                comicNumber = 143,
                comicName = "Parody Week: TFD and Natalie Dee",
                comicDescription = "Some Description",
                comicImageLink = "https://imgs.xkcd.com/comics/tfd_nataliedee.png"
            )
        }

    }
}