package io.shortcut.android.xkcd.comics.favorite.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.shortcut.android.xkcd.comics.favorite.data.FavoriteRepositoryImpl
import io.shortcut.android.xkcd.comics.favorite.domain.usecase.FavoriteByNumberUseCase
import io.shortcut.android.xkcd.comics.favorite.testdataprovider.FavoriteDataProvider
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.map
import io.shortcut.android.xkcd.comics.room.entity.FavoriteEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FavoriteByNumberUseCaseTest {

    // Mock
    private val favoriteRepository: FavoriteRepositoryImpl = mockk()

    // Class for test
    private val favoriteByNumberUseCase = FavoriteByNumberUseCase(favoriteRepository)

    @Test
    fun `when get a row by number then call favoriteByNumber in repository`() = runBlocking {

        // Given
        val number = 1
        coEvery {
            favoriteRepository.getFavoriteByNumber(comicNumber = number)
        } returns ResultModel.Success(FavoriteDataProvider.getFavoriteEntityFilled())

        // When
        favoriteByNumberUseCase.executeAsync(rq = number)

        // Then
        coVerify {
            favoriteRepository.getFavoriteByNumber(comicNumber = number)
        }

    }

    @Test
    fun `when get a row by number then return favoriteEntity if is existed in repository`() =
        runBlocking {

            // Given
            val number = 1
            val favoriteEntity = FavoriteDataProvider.getFavoriteEntityFilled()
            coEvery {
                favoriteRepository.getFavoriteByNumber(comicNumber = number)
            } returns ResultModel.Success(favoriteEntity)

            // When
            var resultFavoriteEntity: FavoriteEntity? = null
            favoriteByNumberUseCase.executeAsync(rq = number).map {
                resultFavoriteEntity = it
            }

            // Then
            assertEquals(favoriteEntity.comicNumber, resultFavoriteEntity?.comicNumber)

        }

}