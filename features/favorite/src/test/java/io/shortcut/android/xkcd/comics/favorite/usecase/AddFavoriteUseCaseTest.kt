package io.shortcut.android.xkcd.comics.favorite.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.shortcut.android.xkcd.comics.favorite.data.FavoriteRepositoryImpl
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.AddFavoriteUseCase
import io.shortcut.android.xkcd.comics.favorite.testdataprovider.FavoriteDataProvider
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.map
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AddFavoriteUseCaseTest {

    // Mock
    private val favoriteRepository: FavoriteRepositoryImpl = mockk()

    // Class for test
    private val favoriteInsertUseCase = AddFavoriteUseCase(favoriteRepository)

    @Test
    fun `when insert a row then call insert in repository`() = runBlocking {

        // Given
        val favoriteEntity = FavoriteDataProvider.getFavoriteEntityFilled()
        coEvery {
            favoriteRepository.addFavorite(any())
        } returns ResultModel.Success(favoriteEntity.comicNumber.toLong())

        // When
        favoriteInsertUseCase.executeAsync(rq = favoriteEntity)

        // Then
        coVerify {
            favoriteRepository.addFavorite(any())
        }

    }

    @Test
    fun `when insert a row if success then return true`() = runBlocking {

        // Given
        val favoriteEntity = FavoriteDataProvider.getFavoriteEntityFilled()
        var isInsertSuccess = false
        coEvery {
            favoriteRepository.addFavorite(any())
        } returns ResultModel.Success(favoriteEntity.comicNumber.toLong())

        // When
        favoriteInsertUseCase.executeAsync(rq = favoriteEntity).map {
            isInsertSuccess = it
        }

        // Then
        assertEquals(true, isInsertSuccess)

    }


    @Test
    fun `when insert a row if fail then return false`() = runBlocking {

        // Given
        val favoriteEntity = FavoriteDataProvider.getFavoriteEntityFilled()
        var isInsertSuccess = true
        coEvery {
            favoriteRepository.addFavorite(any())
        } returns ResultModel.Success(-1)

        // When
        favoriteInsertUseCase.executeAsync(rq = favoriteEntity).map {
            isInsertSuccess = it
        }

        // Then
        assertEquals(false, isInsertSuccess)

    }


}