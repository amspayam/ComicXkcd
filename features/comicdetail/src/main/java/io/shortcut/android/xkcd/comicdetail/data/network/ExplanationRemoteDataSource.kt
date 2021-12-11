package io.shortcut.android.xkcd.comicdetail.data.network

import io.shortcut.android.xkcd.comicdetail.data.entity.ExplanationEntity
import io.shortcut.android.xkcd.comics.repository.ResultModel

interface ExplanationRemoteDataSource {
    suspend fun getComicExplanation(comicNumber: Int, comicTitle: String): ResultModel<ExplanationEntity>
}