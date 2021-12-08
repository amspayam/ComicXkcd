package io.shortcut.android.xkcd.comics.base.transformer


import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AsyncOTransformer<T> constructor(
    private val ioThread: Scheduler = Schedulers.io(),
    private val postExecutionThread: Scheduler = AndroidSchedulers.mainThread()
) : OTransformer<T>() {
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
            .subscribeOn(ioThread)
            .observeOn(postExecutionThread)
    }
}