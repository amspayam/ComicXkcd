package io.shortcut.android.xkcd.comics.favorite.domain.usecase

import io.shortcut.android.xkcd.comics.base.usecase.AsyncSuspendUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.FavoriteRepository
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.map
import io.shortcut.android.xkcd.comics.room.entity.FavoriteEntity

class AddFavoriteUseCase(
    private val repository: FavoriteRepository
) : AsyncSuspendUseCase<FavoriteEntity, ResultModel<Boolean>> {

    override suspend fun executeAsync(rq: FavoriteEntity): ResultModel<Boolean> {
        return repository.addFavorite(comic = rq).map {
            it == rq.comicNumber.toLong()
        }
    }
}