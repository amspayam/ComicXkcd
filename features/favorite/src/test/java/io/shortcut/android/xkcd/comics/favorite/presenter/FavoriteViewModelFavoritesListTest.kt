package io.shortcut.android.xkcd.comics.favorite.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.DeleteFavoriteUseCase
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.FavoriteListUseCase
import io.shortcut.android.xkcd.comics.test.rule.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FavoriteViewModelFavoritesListTest {

    // Rules
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    // Mock
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase = mockk()
    private val favoriteListUseCase: FavoriteListUseCase = mockk()

    // Class for test
    private lateinit var favoriteViewModel: FavoriteViewModel

    @Test
    fun `get items then repository must call`() = runBlocking {

        // Given
        coEvery {
            favoriteListUseCase.executeAsync(Unit)
        } returns mockk()

        // When
        // This is init call and must be call favoriteListUseCase in view model
        favoriteViewModel = FavoriteViewModel(
            favoriteListUseCase = favoriteListUseCase,
            deleteFavoriteUseCase = deleteFavoriteUseCase
        )

        // Then
        coVerify {
            favoriteListUseCase.executeAsync(Unit)
        }

    }

}