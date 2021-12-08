package io.shortcut.android.xkcd.comics.explorer.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.shortcut.android.xkcd.comics.base.view.ViewState
import io.shortcut.android.xkcd.comics.explorer.domain.model.ComicModel
import io.shortcut.android.xkcd.comics.explorer.domain.usecase.LastComicUseCase
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.BaseViewModel

class ExplorerViewModel(
    private val lastComicUseCase: LastComicUseCase
) : BaseViewModel() {

    // State view for request to useCase
    val comicStateViewLiveData = MutableLiveData<ViewState<ComicModel>>()

    init {
        getLastComic()
    }

    private fun getLastComic() {

        // Remove all job
        removeAllJob()

        // Update view for Loading view
        comicStateViewLiveData.value = ViewState.ViewLoading
//        track {
//            lastComicUseCase.executeAsync(kotlin.Unit).executeUseCase({
//
//                // Update view for success data
//                comicStateViewLiveData.postValue(ViewState.ViewData(it))
//
//
//            }, {
//                // Update view for show Error
//                comicStateViewLiveData.postValue(ViewState.ViewError(it.message))
//            })
//        }

    }

}