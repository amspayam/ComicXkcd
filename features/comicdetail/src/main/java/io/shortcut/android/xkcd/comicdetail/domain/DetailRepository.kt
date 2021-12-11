package io.shortcut.android.xkcd.comicdetail.domain

import io.shortcut.android.xkcd.comicdetail.data.entity.ExplanationEntity
import io.shortcut.android.xkcd.comicdetail.domain.model.ExplanationRequestModel
import io.shortcut.android.xkcd.comics.repository.ResultModel

interface DetailRepository {
    suspend fun getComicExplanation(explanationRequestModel: ExplanationRequestModel):
            ResultModel<ExplanationEntity>
}