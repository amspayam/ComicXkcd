package io.shortcut.android.xkcd.comics.base.transformer

import io.reactivex.MaybeTransformer

abstract class MTransformer<T> : MaybeTransformer<T, T>