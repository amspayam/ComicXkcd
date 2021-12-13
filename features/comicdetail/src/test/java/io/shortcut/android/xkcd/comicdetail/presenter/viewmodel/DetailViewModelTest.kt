package io.shortcut.android.xkcd.comicdetail.presenter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.shortcut.android.xkcd.comicdetail.domain.model.ExplanationRequestModel
import io.shortcut.android.xkcd.comicdetail.domain.usecase.ExplanationUseCase
import io.shortcut.android.xkcd.comicdetail.presenter.DetailViewModel
import io.shortcut.android.xkcd.comicdetail.testdataprovider.DetailDataProviderFromJson
import io.shortcut.android.xkcd.comics.base.view.onViewData
import io.shortcut.android.xkcd.comics.base.view.onViewError
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.network.entity.RestErrorResponse
import io.shortcut.android.xkcd.comics.test.rule.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class DetailViewModelTest {

    // Rules
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    // Mock
    private val explanationUseCase: ExplanationUseCase = mockk()

    // Class for test
    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun before() {

        // Initial viewModel
        detailViewModel = DetailViewModel(
            explanationUseCase = explanationUseCase
        )

    }

    @Test
    fun `get comic number 2130 then useCase must execute`() = runBlocking {

        // Given
        val comicNumber = 2130
        val comicTitle = "A Industry Nicknames"
        val explanationResultModel = ExplanationRequestModel(
            comicNumber = comicNumber,
            comicTitle = comicTitle
        )

        coEvery {
            explanationUseCase.executeAsync(explanationResultModel)
        } returns ResultModel.Success(
            DetailDataProviderFromJson.getExplanationByNumber(comicNumber).toModel()
        )

        // When
        detailViewModel.getExplanation(
            comicNumber = comicNumber,
            comicTitle = comicTitle,
            comicImageUrl = "",
            comicDescription = ""
        )

        // Then
        coVerify {
            explanationUseCase.executeAsync(explanationResultModel)
        }

    }

    @Test
    fun `get comic number 2130 then success response`() = runBlocking {

        // Given
        val comicNumber = 2130
        val comicTitle = "A Industry Nicknames"
        val explanationResultModel = ExplanationRequestModel(
            comicNumber = comicNumber,
            comicTitle = comicTitle
        )

        coEvery {
            explanationUseCase.executeAsync(explanationResultModel)
        } returns ResultModel.Success(
            DetailDataProviderFromJson.getExplanationByNumber(comicNumber).toModel()
        )

        // When
        detailViewModel.getExplanation(
            comicNumber = comicNumber,
            comicTitle = comicTitle,
            comicImageUrl = "",
            comicDescription = ""
        )

        // Then
        detailViewModel.detailStateViewLiveData.observeForever {
            it.onViewData { explanationMode ->
                assert(explanationMode.title == "2130: A Industry Nicknames")
            }
        }

    }

    @Test
    fun `get comic number 2130 then error response`() = runBlocking {

        // Given
        val comicNumber = 2130
        val comicTitle = "A Industry Nicknames"
        val explanationResultModel = ExplanationRequestModel(
            comicNumber = comicNumber,
            comicTitle = comicTitle
        )

        coEvery {
            explanationUseCase.executeAsync(explanationResultModel)
        } returns ResultModel.Error(RestErrorResponse(500, "Error"))

        // When
        detailViewModel.getExplanation(
            comicNumber = comicNumber,
            comicTitle = comicTitle,
            comicImageUrl = "",
            comicDescription = ""
        )

        // Then
        detailViewModel.detailStateViewLiveData.observeForever {
            it.onViewError { status, messages ->
                assert(messages == "Error")
            }
        }

    }

}