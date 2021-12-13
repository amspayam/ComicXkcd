package io.shortcut.android.xkcd.comicdetail.testdataprovider

import io.shortcut.android.xkcd.comicdetail.data.entity.ExplanationEntity
import io.shortcut.android.xkcd.comicdetail.domain.model.ExplanationResponseModel
import io.shortcut.android.xkcd.comics.test.dataprovider.DataProviderUtils


class DetailDataProviderFromJson {

    companion object {

        private const val comic_explanation_number_2130_path = "json/comic_explanation_number_2130.json"

        fun getExplanationFilled(): ExplanationEntity {
            return DataProviderUtils.getModelFromFileResource(
                comic_explanation_number_2130_path,
                ExplanationEntity::class.java
            )
        }

        fun getExplanationModelFilledNumber2130(): ExplanationResponseModel {
            return DataProviderUtils.getModelFromFileResource(
                comic_explanation_number_2130_path,
                ExplanationEntity::class.java
            ).toModel()
        }

        fun getExplanationByNumber(comicNumber: Int): ExplanationEntity {
            return DataProviderUtils.getModelFromFileResource(
                "json/comic_explanation_number_${comicNumber}.json",
                ExplanationEntity::class.java
            )
        }

    }
}