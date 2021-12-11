package io.shortcut.android.xkcd.comicdetail.data.network

import io.shortcut.android.xkcd.comicdetail.data.api.ExplanationApiService
import io.shortcut.android.xkcd.comicdetail.data.entity.ExplanationEntity
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.awaitResult

class ExplanationRemoteDataSourceImpl(
    private val apiService: ExplanationApiService
) : ExplanationRemoteDataSource {

    override suspend fun getComicExplanation(
        comicNumber: Int,
        comicTitle: String
    ): ResultModel<ExplanationEntity> {

        val comic = "$comicNumber:_${
            comicTitle.replace(
                " ",
                "_"
            )
        }"

        return apiService.getComicExplanation(
            comicTitle = comic
        ).awaitResult { it }
    }

}