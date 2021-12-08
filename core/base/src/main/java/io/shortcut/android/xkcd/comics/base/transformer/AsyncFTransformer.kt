package io.shortcut.android.xkcd.comics.base.transformer

//class AsyncFTransformer<T> constructor(
//        private val ioThread: Scheduler = Schedulers.io(),
//        private val postExecutionThread: Scheduler = AndroidSchedulers.mainThread()
//) : FTransformer<T>() {
//    override fun apply(upstream: Flowable<T>): Flow.Publisher<T> {
//        return upstream
//                .subscribeOn(ioThread)
//                .observeOn(postExecutionThread)
//    }
//}