package io.shortcut.android.xkcd.comics.base.usecase

interface SyncUseCase<RQ, RS> {
    fun executeSync(rq: RQ): RS
}