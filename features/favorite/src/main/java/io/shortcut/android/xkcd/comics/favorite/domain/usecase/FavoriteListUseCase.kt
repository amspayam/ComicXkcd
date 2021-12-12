package io.shortcut.android.xkcd.comics.favorite.domain.usecase

import androidx.paging.PagingData
import io.shortcut.android.xkcd.comics.base.usecase.AsyncSuspendUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.FavoriteRepository
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.room.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

class FavoriteListUseCase(
    private val repository: FavoriteRepository
) : AsyncSuspendUseCase<Unit, ResultModel<Flow<PagingData<FavoriteEntity>>>> {

    override suspend fun executeAsync(rq: Unit): ResultModel<Flow<PagingData<FavoriteEntity>>> {
        return repository.getFavoriteList()
    }
}