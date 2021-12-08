package io.shortcut.android.xkcd.comics.repository

import io.shortcut.android.xkcd.comics.base.extension.GsonUtils.toObjectByGson
import io.shortcut.android.xkcd.comics.repository.network.entity.RestErrorResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume

sealed class ResultModel<out V> {
    data class Success<V>(val value: V) : ResultModel<V>()
    data class Error(val error: RestErrorResponse) : ResultModel<Nothing>()
}

private const val ERROR_RESPONSE_UNKNOWN = -1

inline fun <R, T> ResultModel<T>.executeUseCase(
    ifSuccess: (value: T) -> R,
    ifError: (error: RestErrorResponse) -> R
): R {
    return when (this) {
        is ResultModel.Success<T> -> ifSuccess(value)
        is ResultModel.Error -> ifError(error)
    }
}

fun <T, R> ResultModel<T>.map(isSuccess: (T) -> R): ResultModel<R> {
    return when (this) {
        is ResultModel.Success -> ResultModel.Success(isSuccess.invoke(value))
        is ResultModel.Error -> ResultModel.Error(error)
    }
}

suspend fun <T, R> Call<T>.awaitResult(map: (T) -> R): ResultModel<R> = enqueue(map)

private suspend fun <T, R> Call<T>.enqueue(
    map: (T) -> R,
    onSuccess: ((response: Response<T>) -> Unit)? = null,
    onFailure: ((throwable: Throwable, errorCode: Int) -> Unit)? = null,
    onCanceled: (() -> Unit)? = null
): ResultModel<R> = suspendCancellableCoroutine { continuation ->
    try {
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, throwable: Throwable) {
                handleError(ERROR_RESPONSE_UNKNOWN, throwable)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    try {
                        onSuccess?.invoke(response)
                        continuation.resume(ResultModel.Success(map(response.body()!!)))
                    } catch (throwable: Throwable) {
                        handleError(response.code(), throwable)
                    }
                } else {
                    handleError(response.code(), HttpException(response))
                }
            }

            private fun handleError(errorCode: Int, throwable: Throwable) {
                onFailure?.invoke(throwable, errorCode)
                continuation.resume(ResultModel.Error(fromNetworkThrowable(throwable)))
            }
        })
    } catch (throwable: Throwable) {
        continuation.resume(ResultModel.Error(fromNetworkThrowable(throwable)))
    }

    continuation.invokeOnCancellation {
        onCanceled?.invoke()
        cancel()
    }
}


/**
 * From network throwable data layer error.
 *
 * @param throwable the throwable
 * @return the data layer error
 */

private const val ERROR_MESSAGE = "Can Not Connect To Server"
fun fromNetworkThrowable(throwable: Throwable): RestErrorResponse {

    throwable.printStackTrace()

    return if (throwable is HttpException) {
        val networkError = throwable.response()
        val errorBodyString = networkError?.errorBody()?.string().toString()
        return try {
            getFlatErrorModel(errorBodyString)
        } catch (e: Exception) {
            getErrorModel(errorBodyString)
        }
    } else {
        RestErrorResponse(-1, ERROR_MESSAGE)
    }

}

@Throws(Exception::class)
fun getFlatErrorModel(json: String?): RestErrorResponse {
    json?.let {
        return json.toObjectByGson(RestErrorResponse::class.java)
    } ?: run {
        return RestErrorResponse(-1, ERROR_MESSAGE)
    }
}

fun getErrorModel(json: String?): RestErrorResponse {
    json?.let {
        return try {
            json.toObjectByGson(RestErrorResponse::class.java)
        } catch (e: Exception) {
            RestErrorResponse(-1, ERROR_MESSAGE)
        }
    } ?: run {
        return RestErrorResponse(-1, ERROR_MESSAGE)
    }
}