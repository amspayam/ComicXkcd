package io.shortcut.android.xkcd.comicdetail.domain.usecase

import io.shortcut.android.xkcd.comicdetail.domain.DetailRepository
import io.shortcut.android.xkcd.comicdetail.domain.model.ExplanationRequestModel
import io.shortcut.android.xkcd.comicdetail.domain.model.ExplanationResponseModel
import io.shortcut.android.xkcd.comics.base.usecase.AsyncSuspendUseCase
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.map

class ExplanationUseCase(
    private val repository: DetailRepository
) : AsyncSuspendUseCase<ExplanationRequestModel, ResultModel<ExplanationResponseModel>> {

    override suspend fun executeAsync(rq: ExplanationRequestModel): ResultModel<ExplanationResponseModel> {
        return repository.getComicExplanation(rq).map {
            it.toModel()
        }
    }
}