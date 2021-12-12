package io.shortcut.android.xkcd.comics.favorite.presenter

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import io.shortcut.android.xkcd.comics.base.view.ViewState
import io.shortcut.android.xkcd.comics.favorite.R
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.DeleteFavoriteUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.FavoriteListUseCase
import io.shortcut.android.xkcd.comics.repository.executeUseCase
import io.shortcut.android.xkcd.comics.room.entity.FavoriteEntity
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.BaseViewModel
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageMaster
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageTypeEnum
import kotlinx.coroutines.flow.Flow

class FavoriteViewModel(
    private val favoriteListUseCase: FavoriteListUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseViewModel() {

    // State view for request to useCase
    val allFavoritesStateViewLiveData =
        MutableLiveData<ViewState<Flow<PagingData<FavoriteEntity>>>>()

    init {
        getAllFavorites()
    }

    private fun getAllFavorites() {
        // Remove all job
        removeAllJob()

        track {
            favoriteListUseCase.executeAsync(Unit).executeUseCase({

                // Update view for success data
                allFavoritesStateViewLiveData.postValue(ViewState.ViewData(it))

            }, {
                // Update view for show Error
                allFavoritesStateViewLiveData.postValue(ViewState.ViewError(it.message))
            })
        }

    }

    fun deleteComic(comicNumber: Int) {

        // Remove all job
        removeAllJob()

        track {
            deleteFavoriteUseCase.executeAsync(comicNumber).executeUseCase({ isDeleteSuccess ->

                // Update view for success data
                if (isDeleteSuccess)
                    message.postValue(
                        MessageMaster(
                            type = MessageTypeEnum.TOAST,
                            messageResourceId = R.string.delete_successful
                        )
                    )

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