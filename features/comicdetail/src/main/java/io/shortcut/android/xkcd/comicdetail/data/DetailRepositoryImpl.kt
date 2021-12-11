package io.shortcut.android.xkcd.comicdetail.data

import io.shortcut.android.xkcd.comicdetail.data.entity.ExplanationEntity
import io.shortcut.android.xkcd.comicdetail.data.network.ExplanationRemoteDataSource
import io.shortcut.android.xkcd.comicdetail.domain.DetailRepository
import io.shortcut.android.xkcd.comicdetail.domain.model.ExplanationRequestModel
import io.shortcut.android.xkcd.comics.repository.ResultModel

class DetailRepositoryImpl(private val dataSource: ExplanationRemoteDataSource) : DetailRepository {

    override suspend fun getComicExplanation(
        explanationRequestModel: ExplanationRequestModel
    ): ResultModel<ExplanationEntity> {
        return dataSource.getComicExplanation(
            comicNumber = explanationRequestModel.comicNumber,
            comicTitle = explanationRequestModel.comicTitle
        )
    }

}