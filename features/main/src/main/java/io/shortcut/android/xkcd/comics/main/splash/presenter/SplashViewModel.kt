package io.shortcut.android.xkcd.comics.main.splash.presenter

import androidx.lifecycle.MutableLiveData
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.BaseViewModel
import kotlinx.coroutines.delay

class SplashViewModel : BaseViewModel() {

    val navigationToMainFragment = MutableLiveData(false)

    init {
        track {
            delay(2000)
            navigationToMainFragment.postValue(true)
        }
    }
}