package io.shortcut.android.xkcd.comics.cachemanager.di

import io.shortcut.android.xkcd.comics.cachemanager.CacheManager
import org.koin.dsl.module

object CachemanagerInjector {

    fun provideDependencies() = module {

        single { CacheManager(get()) }

    }
}