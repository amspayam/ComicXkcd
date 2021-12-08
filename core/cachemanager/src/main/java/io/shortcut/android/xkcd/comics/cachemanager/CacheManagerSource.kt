package io.shortcut.android.xkcd.comics.cachemanager

interface CacheManagerSource {
    fun save(key: String, value: Any?): Boolean
    fun load(key: String, type: Any)
}
