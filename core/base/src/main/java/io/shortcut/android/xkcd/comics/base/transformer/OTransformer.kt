package io.shortcut.android.xkcd.comics.base.transformer

import io.reactivex.ObservableTransformer

abstract class OTransformer<T> : ObservableTransformer<T, T>