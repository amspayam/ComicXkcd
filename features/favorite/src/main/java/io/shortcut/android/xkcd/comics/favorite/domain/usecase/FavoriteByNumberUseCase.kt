package io.shortcut.android.xkcd.comics.favorite.domain.usecase

import io.shortcut.android.xkcd.comics.base.usecase.AsyncSuspendUseCase
import io.shortcut.android.xkcd.comics.database.entity.FavoriteEntity
import io.shortcut.android.xkcd.comics.favorite.domain.FavoriteRepository
import io.shortcut.android.xkcd.comics.repository.ResultModel

class FavoriteByNumberUseCase(
    private val repository: FavoriteRepository
) : AsyncSuspendUseCase<Int, ResultModel<FavoriteEntity?>> {

    override suspend fun executeAsync(rq: Int): ResultModel<FavoriteEntity?> {
        return repository.getFavoriteByNumber(comicNumber = rq)
    }
}