package io.shortcut.android.xkcd.comics.apptest.application

import androidx.multidex.MultiDexApplication
import io.shortcut.android.xkcd.comics.apptest.main.di.MainActivityTestInjector
import io.shortcut.android.xkcd.comics.cachemanager.di.CachemanagerInjector
import io.shortcut.android.xkcd.comics.repository.di.RepositoryInjector
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.*

class ApplicationGlobal : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        initialKoin()

    }

    private fun initialKoin() {
        startKoin {
            androidLogger(Level.ERROR).androidContext(this@ApplicationGlobal)
                .modules(CachemanagerInjector.provideDependencies())
                .modules(RepositoryInjector.provideDependencies())
                .modules(MainActivityTestInjector.provideDependencies())
        }
    }

}