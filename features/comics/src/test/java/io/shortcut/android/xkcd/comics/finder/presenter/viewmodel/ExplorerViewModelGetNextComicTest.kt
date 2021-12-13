package io.shortcut.android.xkcd.comics.finder.presenter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.AddFavoriteUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.DeleteFavoriteUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.FavoriteByNumberUseCase
import io.shortcut.android.xkcd.comics.finder.domain.usecase.ComicByNumberUseCase
import io.shortcut.android.xkcd.comics.finder.domain.usecase.LastComicUseCase
import io.shortcut.android.xkcd.comics.finder.presenter.ComicFinderViewModel
import io.shortcut.android.xkcd.comics.finder.testdataprovider.ComicFinderDataProviderFromJson
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.test.rule.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@ExperimentalCoroutinesApi
@RunWith(Parameterized::class)
class ExplorerViewModelGetNextComicTest(
    private val currentComicNumber: Int,
    private val lastComicNumberAvailable: Int,
    private val requestedComicNumber: Int,
    private val isCanShowNextComic: Boolean
) {

    // <editor-fold defaultState="collapsed" desc="Input Data Parameterized">
    class InputData(
        private val currentComicNumber: Int,
        private val lastComicNumberAvailable: Int,
        private val requestedComicNumber: Int = 0,
        private val isCanShowNextComic: Boolean
    ) {
        fun toArray(): Array<Any> {
            return arrayOf(
                currentComicNumber,
                lastComicNumberAvailable,
                requestedComicNumber,
                isCanShowNextComic
            )
        }
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters(name = "currentComicNumber:{0}, lastComicNumberAvailable:{1}, requestedComicNumber:{2}, isCanShowNextComic:{3}")
        fun data() = listOf(

            // Valid
            InputData(
                currentComicNumber = 220,
                lastComicNumberAvailable = 2130,
                requestedComicNumber = 221,
                isCanShowNextComic = true
            ).toArray(),
            InputData(
                currentComicNumber = 1,
                lastComicNumberAvailable = 10,
                requestedComicNumber = 2,
                isCanShowNextComic = true
            ).toArray(),
            InputData(
                currentComicNumber = 5,
                lastComicNumberAvailable = 6,
                requestedComicNumber = 6,
                isCanShowNextComic = true
            ).toArray(),


            // Not valid
            InputData(
                currentComicNumber = 1,
                lastComicNumberAvailable = 1,
                isCanShowNextComic = false
            ).toArray(),
            InputData(
                currentComicNumber = 121,
                lastComicNumberAvailable = 120,
                isCanShowNextComic = false
            ).toArray(),
            InputData(
                currentComicNumber = 15,
                lastComicNumberAvailable = 15,
                isCanShowNextComic = false
            ).toArray()

        )
    }
    // </editor-fold>

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
    fun `nextComic then invoke getComicByNumber`() = runBlocking {

        // Given
        coEvery {
            comicByNumberUseCase.executeAsync(currentComicNumber)
        } returns ResultModel.Success(
            ComicFinderDataProviderFromJson.getComicEntityFilled().toModel()
        )

        // When
        comicFinderViewModel.nextComic(
            comicNumber = currentComicNumber,
            lastComicNumber = lastComicNumberAvailable
        )

        // Then
        if (isCanShowNextComic) {
            coVerify {
                comicByNumberUseCase.executeAsync(requestedComicNumber)
            }
        } else {
            coVerify(exactly = 0) {
                comicByNumberUseCase.executeAsync(any())
            }
        }

    }

}