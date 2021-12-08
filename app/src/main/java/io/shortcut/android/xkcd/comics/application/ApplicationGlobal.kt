package io.shortcut.android.xkcd.comics.application

import androidx.multidex.MultiDexApplication
import io.shortcut.android.xkcd.comics.cachemanager.di.CachemanagerInjector
import io.shortcut.android.xkcd.comics.explorer.di.ExplorerInjector
import io.shortcut.android.xkcd.comics.main.mainactivity.di.MainActivityInjector
import io.shortcut.android.xkcd.comics.main.splash.di.SplashInjector
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
                /* Features */
//                .modules(SharedInjector.provideDependencies())
                .modules(SplashInjector.provideDependencies())
                .modules(MainActivityInjector.provideDependencies())
                .modules(ExplorerInjector.provideDependencies())
//                .modules(RandomInjector.provideDependencies())
//                .modules(ComicDetailInjector.provideDependencies())
//                .modules(FavoriteInjector.provideDependencies())
//                .modules(SearchComicsInjector.provideDependencies())
        }
    }

}