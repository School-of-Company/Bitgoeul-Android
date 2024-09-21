package com.msg.common.result

import kotlinx.coroutines.flow.*

sealed interface Result<out T> {
    data class Success<T>(val data: T): Result<T>
    data class Error(val exception: Throwable): Result<Nothing>
    data object Loading: Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> = map<T, Result<T>> { Result.Success(it) }
    .onStart { emit(Result.Loading) }
    .catch { emit(Result.Error(it)) }