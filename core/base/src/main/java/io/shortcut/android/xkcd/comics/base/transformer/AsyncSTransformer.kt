package io.shortcut.android.xkcd.comics.base.transformer

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AsyncSTransformer<T> constructor(
    private val ioThread: Scheduler = Schedulers.io(),
    private val postExecutionThread: Scheduler = AndroidSchedulers.mainThread()
) : STransformer<T>() {
    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
            .subscribeOn(ioThread)
            .observeOn(postExecutionThread)
    }
}