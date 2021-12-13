package io.shortcut.android.xkcd.comics.finder.presenter

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import io.shortcut.android.xkcd.comics.base.view.ViewState
import io.shortcut.android.xkcd.comics.database.entity.FavoriteEntity
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.AddFavoriteUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.DeleteFavoriteUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.FavoriteByNumberUseCase
import io.shortcut.android.xkcd.comics.finder.domain.model.ComicModel
import io.shortcut.android.xkcd.comics.finder.domain.usecase.ComicByNumberUseCase
import io.shortcut.android.xkcd.comics.finder.domain.usecase.LastComicUseCase
import io.shortcut.android.xkcd.comics.repository.executeUseCase
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.BaseViewModel
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageMaster
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageTypeEnum

class ComicFinderViewModel(
    private val lastComicUseCase: LastComicUseCase,
    private val comicByNumberUseCase: ComicByNumberUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val favoriteByNumberUseCase: FavoriteByNumberUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
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

    // Favorite
    val isFavoriteComic = MutableLiveData(false)

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

                // Update buttons for state (enable or disable)
                updateControllers(
                    lastComicNumber = lastComicNumber,
                    currentComicNumber = latestComic.number
                )

                // Favorite
                checkFavoriteByNumberState(comicNumber = latestComic.number)

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

                    // Update buttons for state (enable or disable)
                    updateControllers(
                        lastComicNumber = lastComicNumber,
                        currentComicNumber = latestComic.number
                    )

                    // Favorite
                    checkFavoriteByNumberState(comicNumber = latestComic.number)

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

    fun changeFavoriteComicState(comic: ComicModel) {

        // Remove all job
        removeAllJob()

        track {
            if (isFavoriteComic.value == true) {
                // Delete from Database
                deleteFavoriteUseCase.executeAsync(comic.number)
                    .executeUseCase({ isDeleteSuccess ->
                        // Update view for success data
                        isFavoriteComic.postValue(!isDeleteSuccess)

                    }, {
                        // Update view for show Error
                        message.postValue(
                            MessageMaster(
                                type = MessageTypeEnum.SNACK_BAR,
                                text = it.message
                            )
                        )
                    })
            } else {
                // Add to Database
                addFavoriteUseCase.executeAsync(
                    FavoriteEntity(
                        comicNumber = comic.number,
                        comicName = comic.title,
                        comicDescription = comic.description,
                        comicImageLink = comic.imageLink
                    )
                )
                    .executeUseCase({ isInsertSuccess ->
                        // Update view for success data
                        isFavoriteComic.postValue(isInsertSuccess)
                    }, {
                        // Update view for show Error
                        message.postValue(
                            MessageMaster(
                                type = MessageTypeEnum.SNACK_BAR,
                                text = it.message
                            )
                        )
                    })
            }
        }

    }

    private fun checkFavoriteByNumberState(comicNumber: Int) {
        track {
            favoriteByNumberUseCase.executeAsync(comicNumber).executeUseCase({ favorite ->
                isFavoriteComic.postValue(favorite != null)
            }, {
                // Update view for show Error
                message.postValue(
                    MessageMaster(
                        type = MessageTypeEnum.SNACK_BAR,
                        text = it.message
                    )
                )
            })
        }
    }

}