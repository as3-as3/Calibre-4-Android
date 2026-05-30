package com.calibre.android.util

import kotlinx.coroutines.CancellationException

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

suspend inline fun <T> safeCall(
    block: suspend () -> T
): Result<T> = try {
    Result.Success(block())
} catch (e: CancellationException) {
    throw e
} catch (e: Exception) {
    Result.Failure(e)
}
