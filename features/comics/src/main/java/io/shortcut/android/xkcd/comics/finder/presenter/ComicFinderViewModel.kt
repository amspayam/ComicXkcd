package io.shortcut.android.xkcd.comics.finder.presenter

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import io.shortcut.android.xkcd.comics.base.view.ViewState
import io.shortcut.android.xkcd.comics.finder.domain.model.ComicModel
import io.shortcut.android.xkcd.comics.finder.domain.usecase.ComicByNumberUseCase
import io.shortcut.android.xkcd.comics.finder.domain.usecase.LastComicUseCase
import io.shortcut.android.xkcd.comics.repository.executeUseCase
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.BaseViewModel

class ComicFinderViewModel(
    private val lastComicUseCase: LastComicUseCase,
    private val comicByNumberUseCase: ComicByNumberUseCase,
) : BaseViewModel() {

    // State view for request to useCase
    val comicStateViewLiveData = MutableLiveData<ViewState<ComicModel>>()

    // State view for controllers status
    val comicControllerStateViewLiveData = MutableLiveData<Boolean>()
    val previousStateViewLiveData = MutableLiveData<Boolean>()
    val nextStateViewLiveData = MutableLiveData<Boolean>()

    var firstComicNumber = 1
    var lastComicNumber = 0
    lateinit var latestComic: ComicModel

    init {
        getLastComic()
    }

    fun getLastComic() {

        // Remove all job
        removeAllJob()

        // Update view for Loading view
        comicStateViewLiveData.value = ViewState.ViewLoading
        track {
            lastComicUseCase.executeAsync(Unit).executeUseCase({

                // Save the last comic number
                lastComicNumber = it.number

                // Save the latest comic number to surf
                latestComic = it

                // Update view for success data
                comicStateViewLiveData.postValue(ViewState.ViewData(it))

                updateControllers(
                    lastComicNumber = lastComicNumber,
                    currentComicNumber = latestComic.number
                )

            }, {
                // Update view for show Error
                comicStateViewLiveData.postValue(ViewState.ViewError(it.message))
            })
        }

    }

    @VisibleForTesting
    fun getComicByNumber(comicNumber: Int) {

        // Remove all job
        removeAllJob()

        // Update view for Loading view
        comicStateViewLiveData.value = ViewState.ViewLoading
        track {
            comicByNumberUseCase.executeAsync(comicNumber)
                .executeUseCase({

                    // Save the latest comic number to surf
                    latestComic = it

                    // Update view for success data
                    comicStateViewLiveData.postValue(ViewState.ViewData(it))

                    updateControllers(
                        lastComicNumber = lastComicNumber,
                        currentComicNumber = latestComic.number
                    )

                }, {
                    // Update view for show Error
                    comicStateViewLiveData.postValue(ViewState.ViewError(it.message))
                })
        }

    }

    // generated random from 1 to lastComicNumber included
    @VisibleForTesting
    fun randomComic(lastComicNumber: Int): Int = (firstComicNumber..lastComicNumber).random()

    @VisibleForTesting
    fun updateControllers(lastComicNumber: Int, currentComicNumber: Int) {
        // Update controller buttons view
        comicControllerStateViewLiveData.postValue(lastComicNumber != 0)

        // Update next button view
        nextStateViewLiveData.postValue(currentComicNumber < lastComicNumber)

        // Update previous button view
        previousStateViewLiveData.postValue(currentComicNumber > firstComicNumber)
    }

    fun nextComic(comicNumber: Int, lastComicNumber: Int) {
        if (comicNumber < lastComicNumber)
            getComicByNumber(comicNumber + 1)
    }

    fun previousComic(comicNumber: Int, firstComicNumber: Int) {
        if (comicNumber > firstComicNumber)
            getComicByNumber(comicNumber - 1)
    }

    fun firstComic() {
        getComicByNumber(firstComicNumber)
    }

    fun randomComic() {
        getComicByNumber(randomComic(lastComicNumber = lastComicNumber))
    }

}