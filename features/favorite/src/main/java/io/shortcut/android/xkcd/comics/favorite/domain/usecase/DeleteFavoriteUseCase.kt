package io.shortcut.android.xkcd.comics.favorite.domain.usecase

import io.shortcut.android.xkcd.comics.base.usecase.AsyncSuspendUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.FavoriteRepository
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.map

class DeleteFavoriteUseCase(
    private val repository: FavoriteRepository
) : AsyncSuspendUseCase<Int, ResultModel<Boolean>> {

    override suspend fun executeAsync(rq: Int): ResultModel<Boolean> {
        return repository.deleteFavorite(comicNumber = rq).map {
            it != 0
        }
    }
}