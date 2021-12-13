package io.shortcut.android.xkcd.comics.favorite.data

import androidx.paging.PagingData
import io.shortcut.android.xkcd.comics.database.entity.FavoriteEntity
import io.shortcut.android.xkcd.comics.favorite.data.local.FavoriteLocalDataSource
import io.shortcut.android.xkcd.comics.favorite.domain.FavoriteRepository
import io.shortcut.android.xkcd.comics.repository.ResultModel
import io.shortcut.android.xkcd.comics.repository.network.entity.RestErrorResponse
import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl(
    private val dataSource: FavoriteLocalDataSource
) : FavoriteRepository {

    override suspend fun getFavoriteList(
        prefetchDistance: Int,
        pageSize: Int,
        enablePlaceholders: Boolean
    ): ResultModel<Flow<PagingData<FavoriteEntity>>> {
        return try {
            ResultModel.Success(
                dataSource.getByPagination(
                    prefetchDistance = prefetchDistance,
                    pageSize = pageSize,
                    enablePlaceholders = enablePlaceholders
                )
            )
        } catch (exception: Exception) {
            ResultModel.Error(
                RestErrorResponse(
                    status = 0,
                    message = exception.localizedMessage ?: "FavoriteList Error"
                )
            )
        }
    }

    override suspend fun getFavoriteByNumber(comicNumber: Int): ResultModel<FavoriteEntity> {
        return try {
            ResultModel.Success(dataSource.getByNumber(comicNumber))
        } catch (exception: Exception) {
            ResultModel.Error(
                RestErrorResponse(
                    status = 0,
                    message = exception.localizedMessage ?: "FavoriteByNumber Error"
                )
            )
        }
    }

    override suspend fun addFavorite(comic: FavoriteEntity): ResultModel<Long> {
        val favoriteEntity = FavoriteEntity(
            comicNumber = comic.comicNumber,
            comicName = comic.comicName,
            comicDescription = comic.comicDescription,
            comicImageLink = comic.comicImageLink
        )
        return try {
            ResultModel.Success(
                dataSource.insert(favoriteEntity = favoriteEntity)
            )
        } catch (exception: Exception) {
            ResultModel.Error(
                RestErrorResponse(
                    status = 0,
                    message = exception.localizedMessage ?: "AddFavorite Error"
                )
            )
        }
    }

    override suspend fun deleteFavorite(comicNumber: Int): ResultModel<Int> {
        return try {
            ResultModel.Success(dataSource.delete(number = comicNumber))
        } catch (exception: Exception) {
            ResultModel.Error(
                RestErrorResponse(
                    status = 0,
                    message = exception.localizedMessage ?: "DeleteFavorite Error"
                )
            )
        }
    }

}