package io.shortcut.android.xkcd.comics.favorite

import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import io.shortcut.android.xkcd.comics.favorite.data.local.FavoriteLocalDataSourceImpl
import io.shortcut.android.xkcd.comics.favorite.testdataprovider.FavoriteDataProvider
import io.shortcut.android.xkcd.comics.room.dao.FavoriteDao
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class FavoriteLocalDataSourceImplTest {

    private val favoriteDao: FavoriteDao = mockk()
    private val dataSource: FavoriteLocalDataSourceImpl =
        FavoriteLocalDataSourceImpl(favoriteDao)


    @Test
    fun `insert favorite into DB`() = runBlocking {

        // Given
        val favoriteEntity = FavoriteDataProvider.getFavoriteEntityFilled()
        coEvery {
            favoriteDao.insert(favoriteEntity)
        } returns 1L

        // When
        dataSource.insert(favoriteEntity = favoriteEntity)

        // Then
        verify {
            favoriteDao.insert(favoriteEntity)
        }

    }

}