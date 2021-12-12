package io.shortcut.android.xkcd.comics.favorite.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.shortcut.android.xkcd.comics.favorite.data.FavoriteRepositoryImpl
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.DeleteFavoriteUseCase
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.map
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DeleteFavoriteUseCaseTest {

    // Mock
    private val favoriteRepository: FavoriteRepositoryImpl = mockk()

    // Class for test
    private val favoriteDeleteUseCase = DeleteFavoriteUseCase(favoriteRepository)

    @Test
    fun `when delete a number then call delete in repository`() = runBlocking {

        // Given
        val number = 1
        coEvery {
            favoriteRepository.deleteFavorite(comicNumber = number)
        } returns ResultModel.Success(1)

        // When
        favoriteDeleteUseCase.executeAsync(rq = number)

        // Then
        coVerify {
            favoriteRepository.deleteFavorite(comicNumber = number)
        }

    }

    @Test
    fun `when delete a number then return true if is existed in repository`() = runBlocking {

        // Given
        val number = 1
        var resultDelete = false
        coEvery {
            favoriteRepository.deleteFavorite(comicNumber = number)
        } returns ResultModel.Success(1)// One means item deleted

        // When
        favoriteDeleteUseCase.executeAsync(rq = number).map {
            resultDelete = it
        }

        // Then
        assertEquals(true, resultDelete)

    }

    @Test
    fun `when delete a number then return false if is not existed in repository`() = runBlocking {

        // Given
        val number = 1
        var resultDelete = true
        coEvery {
            favoriteRepository.deleteFavorite(comicNumber = number)
        } returns ResultModel.Success(0) // Zero means not item deleted

        // When
        favoriteDeleteUseCase.executeAsync(rq = number).map {
            resultDelete = it
        }

        // Then
        assertEquals(false, resultDelete)

    }

}