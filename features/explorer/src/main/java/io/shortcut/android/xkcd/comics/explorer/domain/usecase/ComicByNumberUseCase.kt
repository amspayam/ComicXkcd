package io.shortcut.android.xkcd.comics.explorer.domain.usecase

import io.shortcut.android.xkcd.comics.base.usecase.AsyncSuspendUseCase
import io.shortcut.android.xkcd.comics.explorer.domain.ComicsRepository
import io.shortcut.android.xkcd.comics.explorer.domain.model.ComicModel
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.map

class ComicByNumberUseCase(
    private val repository: ComicsRepository
) : AsyncSuspendUseCase<Int, ResultModel<ComicModel>> {

    override suspend fun executeAsync(rq: Int): ResultModel<ComicModel> {
        return repository.getComicByNumber(rq).map {
            it.toModel()
        }
    }
}