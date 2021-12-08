package io.shortcut.android.xkcd.comics.base.usecase

interface AsyncSuspendUseCase<RQ, RS> {
    suspend fun executeAsync(rq: RQ): RS
}