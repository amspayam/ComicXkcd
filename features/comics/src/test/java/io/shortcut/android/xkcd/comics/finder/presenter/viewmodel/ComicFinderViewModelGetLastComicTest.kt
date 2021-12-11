package io.shortcut.android.xkcd.comics.finder.presenter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.shortcut.android.xkcd.comics.base.view.onViewData
import io.shortcut.android.xkcd.comics.base.view.onViewError
import io.shortcut.android.xkcd.comics.finder.domain.usecase.ComicByNumberUseCase
import io.shortcut.android.xkcd.comics.finder.domain.usecase.LastComicUseCase
import io.shortcut.android.xkcd.comics.finder.presenter.ComicFinderViewModel
import io.shortcut.android.xkcd.comics.finder.testdataprovider.ComicFinderDataProviderFromJson
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.network.entity.RestErrorResponse
import io.shortcut.test.rule.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ComicFinderViewModelGetLastComicTest {

    // Rules
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    // Mock
    private val lastComicUseCase: LastComicUseCase = mockk()
    private val comicByNumberUseCase: ComicByNumberUseCase = mockk()

    // Class for test
    private lateinit var comicFinderViewModel: ComicFinderViewModel

    @Test
    fun `get last comic in init then useCase must execute`() = runBlocking {

        // Given
        coEvery {
            lastComicUseCase.executeAsync(Unit)
        } returns ResultModel.Success(ComicFinderDataProviderFromJson.getComicModelFilledNumber2130())

        // When
        // This is init call and must be call getLastComic in view model
        comicFinderViewModel = ComicFinderViewModel(
            lastComicUseCase = lastComicUseCase,
            comicByNumberUseCase = comicByNumberUseCase
        )

        // Then
        coVerify {
            lastComicUseCase.executeAsync(Unit)
        }

    }

    @Test
    fun `get last comic in init then success response`() = runBlocking {

        // Given
        coEvery {
            lastComicUseCase.executeAsync(Unit)
        } returns ResultModel.Success(ComicFinderDataProviderFromJson.getComicModelFilledNumber2130())

        // When
        // This is init call and must be call getLastComic in view model
        comicFinderViewModel = ComicFinderViewModel(
            lastComicUseCase = lastComicUseCase,
            comicByNumberUseCase = comicByNumberUseCase
        )

        // Then
        comicFinderViewModel.comicStateViewLiveData.observeForever {
            it.onViewData { comicModel ->
                assert(comicModel.number == 2130)
            }
        }

    }

    @Test
    fun `get last comic in init then error response`() = runBlocking {

        // Given
        coEvery {
            lastComicUseCase.executeAsync(Unit)
        } returns ResultModel.Error(RestErrorResponse(500, "Error"))

        // When
        // This is init call and must be call getLastComic in view model
        comicFinderViewModel = ComicFinderViewModel(
            lastComicUseCase = lastComicUseCase,
            comicByNumberUseCase = comicByNumberUseCase
        )

        // Then
        comicFinderViewModel.comicStateViewLiveData.observeForever {
            it.onViewError { status, messages ->
                assert(messages == "Error")
            }
        }

    }

}