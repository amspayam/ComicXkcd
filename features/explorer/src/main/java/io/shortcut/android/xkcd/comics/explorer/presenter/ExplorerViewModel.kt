package io.shortcut.android.xkcd.comics.explorer.presenter

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.shortcut.android.xkcd.comics.base.view.ViewState
import io.shortcut.android.xkcd.comics.explorer.domain.model.ComicModel
import io.shortcut.android.xkcd.comics.explorer.domain.usecase.ComicByNumberUseCase
import io.shortcut.android.xkcd.comics.explorer.domain.usecase.LastComicUseCase
import io.shortcut.android.xkcd.comics.repository.executeUseCase
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.BaseViewModel

class ExplorerViewModel(
    private val lastComicUseCase: LastComicUseCase,
    private val comicByNumberUseCase: ComicByNumberUseCase,
) : BaseViewModel() {

    // State view for request to useCase
    val comicStateViewLiveData = MutableLiveData<ViewState<ComicModel>>()
    val randomStateViewLiveData = MutableLiveData<Boolean>()
    val previousStateViewLiveData = MutableLiveData<Boolean>()
    val nextStateViewLiveData = MutableLiveData<Boolean>()

    var lastComicNumber = 0
    var latestComicNumber = 0

    init {
        getLastComic()
    }

    private fun getLastComic() {

        // Remove all job
        removeAllJob()

        // Update view for Loading view
        comicStateViewLiveData.value = ViewState.ViewLoading
        track {
            lastComicUseCase.executeAsync(Unit).executeUseCase({

                // Save the last comic number
                lastComicNumber = it.number

                // Save the latest comic number to surf
                latestComicNumber = it.number

                // Update view for success data
                comicStateViewLiveData.postValue(ViewState.ViewData(it))

                // Update random button view
                randomStateViewLiveData.postValue(true)

                // Update next button view
                nextStateViewLiveData.postValue(false)


            }, {
                // Update view for show Error
                comicStateViewLiveData.postValue(ViewState.ViewError(it.message))
            })
        }

    }

    fun getRandomComic() {

        // Remove all job
        removeAllJob()

        // Update view for Loading view
        comicStateViewLiveData.value = ViewState.ViewLoading
        track {
            comicByNumberUseCase.executeAsync(randomComic(lastComicNumber))
                .executeUseCase({

                    // Save the latest comic number to surf
                    latestComicNumber = it.number

                    // Update view for success data
                    comicStateViewLiveData.postValue(ViewState.ViewData(it))

                    // Update next button view
                    nextStateViewLiveData.postValue(it.number < lastComicNumber)

                    // Update previous button view
                    previousStateViewLiveData.postValue(it.number > 1)

                }, {
                    // Update view for show Error
                    comicStateViewLiveData.postValue(ViewState.ViewError(it.message))
                })
        }

    }

    fun getComicByNumber(comicNumber: Int) {

        // Remove all job
        removeAllJob()

        // Update view for Loading view
        comicStateViewLiveData.value = ViewState.ViewLoading
        track {
            comicByNumberUseCase.executeAsync(comicNumber)
                .executeUseCase({

                    // Save the latest comic number to surf
                    latestComicNumber = it.number

                    // Update view for success data
                    comicStateViewLiveData.postValue(ViewState.ViewData(it))

                    // Update next button view
                    nextStateViewLiveData.postValue(it.number < lastComicNumber)

                    // Update previous button view
                    previousStateViewLiveData.postValue(it.number > 1)

                }, {
                    // Update view for show Error
                    comicStateViewLiveData.postValue(ViewState.ViewError(it.message))
                })
        }

    }

    // generated random from 1 to lastComicNumber included
    @VisibleForTesting
    fun randomComic(lastComicNumber: Int): Int = (1..lastComicNumber).random()

}