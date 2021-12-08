package io.shortcut.android.xkcd.comics.base.transformer

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AsyncCTransformer constructor(
    private val ioThread: Scheduler = Schedulers.io(),
    private val postExecutionThread: Scheduler = AndroidSchedulers.mainThread()
) : CTransformer() {
    override fun apply(upstream: Completable): CompletableSource {
        return upstream
            .subscribeOn(ioThread)
            .observeOn(postExecutionThread)
    }
}