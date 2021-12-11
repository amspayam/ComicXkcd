package io.shortcut.android.xkcd.comics.finder.presenter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import io.shortcut.android.xkcd.comics.finder.domain.usecase.ComicByNumberUseCase
import io.shortcut.android.xkcd.comics.finder.domain.usecase.LastComicUseCase
import io.shortcut.android.xkcd.comics.finder.presenter.ComicFinderViewModel
import io.shortcut.android.xkcd.comics.finder.testdataprovider.ComicFinderDataProviderFromJson
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.test.rule.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ComicFinderViewModelRandomTest {

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

    @Before
    fun before() {

        // Mock for init
        coEvery {
            lastComicUseCase.executeAsync(Unit)
        } returns ResultModel.Success(ComicFinderDataProviderFromJson.getComicModelFilledNumber2130())

        // Initial viewModel
        comicFinderViewModel = ComicFinderViewModel(
            lastComicUseCase = lastComicUseCase,
            comicByNumberUseCase = comicByNumberUseCase
        )

    }

    @Test
    fun `creating random comic number in range of 1 to last comic number`() {

        // Given
        val lastComicNumber = 2130

        repeat(100) {
            // When
            val result = comicFinderViewModel.randomComic(lastComicNumber)

            // Then
            Assert.assertTrue(result in 1..lastComicNumber)
        }
    }

}