package io.shortcut.android.xkcd.comics.finder.domain.usecase

import io.shortcut.android.xkcd.comics.base.usecase.AsyncSuspendUseCase
import io.shortcut.android.xkcd.comics.finder.domain.ComicFinderRepository
import io.shortcut.android.xkcd.comics.finder.domain.model.ComicModel
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.map

class ComicByNumberUseCase(
    private val repository: ComicFinderRepository
) : AsyncSuspendUseCase<Int, ResultModel<ComicModel>> {

    override suspend fun executeAsync(rq: Int): ResultModel<ComicModel> {
        return repository.getComicByNumber(rq).map {
            it.toModel()
        }
    }
}