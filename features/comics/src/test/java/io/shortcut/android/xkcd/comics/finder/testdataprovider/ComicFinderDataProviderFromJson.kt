package io.shortcut.android.xkcd.comics.finder.testdataprovider

import io.shortcut.android.xkcd.comics.finder.data.entity.ComicEntity
import io.shortcut.android.xkcd.comics.finder.domain.model.ComicModel
import io.shortcut.test.dataprovider.DataProviderUtils


class ComicFinderDataProviderFromJson {

    companion object {

        private const val comic_number_2130_path = "json/comic_number_2130.json"

        fun getComicEntityFilled(): ComicEntity {
            return DataProviderUtils.getModelFromFileResource(
                comic_number_2130_path,
                ComicEntity::class.java
            )
        }

        fun getComicModelFilledNumber2130(): ComicModel {
            return DataProviderUtils.getModelFromFileResource(
                comic_number_2130_path,
                ComicEntity::class.java
            ).toModel()
        }

        fun getComicByNumber(comicNumber: Int): ComicEntity {
            return DataProviderUtils.getModelFromFileResource(
                "json/comic_number_${comicNumber}.json",
                ComicEntity::class.java
            )
        }

    }
}