package io.shortcut.android.xkcd.comicdetail.presenter

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import io.shortcut.android.xkcd.comicdetail.domain.model.ExplanationRequestModel
import io.shortcut.android.xkcd.comicdetail.domain.model.ExplanationResponseModel
import io.shortcut.android.xkcd.comicdetail.domain.usecase.ExplanationUseCase
import io.shortcut.android.xkcd.comics.base.view.ViewState
import io.shortcut.android.xkcd.comics.repository.executeUseCase
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.BaseViewModel

class DetailViewModel(
    private val explanationUseCase: ExplanationUseCase
) : BaseViewModel() {

    // State view for request to useCase
    val detailStateViewLiveData = MutableLiveData<ViewState<ExplanationResponseModel>>()

    fun bindArguments(args: DetailFragmentArgs) {

        getExplanation(
            comicNumber = args.comicNumber,
            comicTitle = args.comicTitle,
            comicImageUrl = args.comicImageUrl,
            comicDescription = args.comicDescription,
        )

    }

    @VisibleForTesting
    fun getExplanation(
        comicNumber: Int,
        comicTitle: String,
        comicImageUrl: String,
        comicDescription: String
    ) {

        // Remove all job
        removeAllJob()

        // Update view for Loading view
        detailStateViewLiveData.value = ViewState.ViewLoading

        track {
            explanationUseCase.executeAsync(
                ExplanationRequestModel(
                    comicNumber = comicNumber,
                    comicTitle = comicTitle
                )
            ).executeUseCase({

                // Adding image url to response entity to show comic image
                it.imageUrl = comicImageUrl
                it.descripton = comicDescription

                // Update view for success data
                detailStateViewLiveData.postValue(ViewState.ViewData(it))

            }, {
                // Update view for show Error
                detailStateViewLiveData.postValue(ViewState.ViewError(it.message))
            })
        }


    }


}