package io.shortcut.android.xkcd.comics.explorer.domain.usecase

import io.shortcut.android.xkcd.comics.base.usecase.AsyncSuspendUseCase
import io.shortcut.android.xkcd.comics.explorer.domain.ComicsRepository
import io.shortcut.android.xkcd.comics.explorer.domain.model.ComicModel
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.map

class LastComicUseCase(
    private val repository: ComicsRepository
) : AsyncSuspendUseCase<Unit, ResultModel<ComicModel>> {

    override suspend fun executeAsync(rq: Unit): ResultModel<ComicModel> {
        return repository.getLastComic().map {
            it.toModel()
        }
    }
}