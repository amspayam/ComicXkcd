package io.shortcut.android.xkcd.comics.base.transformer

import io.reactivex.Maybe
import io.reactivex.MaybeSource
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AsyncMTransformer<T> constructor(
    private val ioThread: Scheduler = Schedulers.io(),
    private val postExecutionThread: Scheduler = AndroidSchedulers.mainThread()
) : MTransformer<T>() {
    override fun apply(upstream: Maybe<T>): MaybeSource<T> {
        return upstream
            .subscribeOn(ioThread)
            .observeOn(postExecutionThread)
    }
}