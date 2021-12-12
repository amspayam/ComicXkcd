package io.shortcut.android.xkcd.comics.finder.presenter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.shortcut.android.xkcd.comics.base.view.onViewData
import io.shortcut.android.xkcd.comics.base.view.onViewError
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.AddFavoriteUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.DeleteFavoriteUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.FavoriteByNumberUseCase
import io.shortcut.android.xkcd.comics.finder.domain.usecase.ComicByNumberUseCase
import io.shortcut.android.xkcd.comics.finder.domain.usecase.LastComicUseCase
import io.shortcut.android.xkcd.comics.finder.presenter.ComicFinderViewModel
import io.shortcut.android.xkcd.comics.finder.testdataprovider.ComicFinderDataProviderFromJson
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.network.entity.RestErrorResponse
import io.shortcut.test.rule.MainCoroutineRule
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
class ComicFinderViewModelGetComicByNumberTest {

    // Rules
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    // Mock
    private val lastComicUseCase: LastComicUseCase = mockk()
    private val comicByNumberUseCase: ComicByNumberUseCase = mockk()
    private val addFavoriteUseCase: AddFavoriteUseCase = mockk()
    private val favoriteByNumberUseCase: FavoriteByNumberUseCase = mockk()
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase = mockk()

    // Class for test
    private lateinit var comicFinderViewModel: ComicFinderViewModel

    @Before
    fun before() {

        // Mock for init
        coEvery {
            lastComicUseCase.executeAsync(Unit)
        } returns ResultModel.Success(ComicFinderDataProviderFromJson.getComicModelFilledNumber2130())

        // Initial viewModel
        comicFinderViewModel = ComicFinderViewModel(
            lastComicUseCase = lastComicUseCase,
            comicByNumberUseCase = comicByNumberUseCase,
            addFavoriteUseCase = addFavoriteUseCase,
            favoriteByNumberUseCase = favoriteByNumberUseCase,
            deleteFavoriteUseCase = deleteFavoriteUseCase
        )

    }

    @Test
    fun `get comic number 1 then useCase must execute`() = runBlocking {

        // Given
        val comicNumber = 1
        coEvery {
            comicByNumberUseCase.executeAsync(comicNumber)
        } returns ResultModel.Success(
            ComicFinderDataProviderFromJson.getComicByNumber(comicNumber).toModel()
        )

        // When
        comicFinderViewModel.getComicByNumber(comicNumber)

        // Then
        coVerify {
            comicByNumberUseCase.executeAsync(comicNumber)
        }

    }

    @Test
    fun `get comic number 1 then success response`() = runBlocking {

        // Given
        val comicNumber = 1
        coEvery {
            comicByNumberUseCase.executeAsync(comicNumber)
        } returns ResultModel.Success(
            ComicFinderDataProviderFromJson.getComicByNumber(comicNumber).toModel()
        )

        // When
        comicFinderViewModel.getComicByNumber(comicNumber)

        // Then
        comicFinderViewModel.comicStateViewLiveData.observeForever {
            it.onViewData { comicModel ->
                assert(comicModel.number == 1)
            }
        }

    }

    @Test
    fun `get comic number 1 then error response`() = runBlocking {

        // Given
        val comicNumber = 1
        coEvery {
            comicByNumberUseCase.executeAsync(comicNumber)
        } returns ResultModel.Error(RestErrorResponse(500, "Error"))

        // When
        comicFinderViewModel.getComicByNumber(comicNumber)

        // Then
        comicFinderViewModel.comicStateViewLiveData.observeForever {
            it.onViewError { status, messages ->
                assert(messages == "Error")
            }
        }

    }

}