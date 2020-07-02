package dev.hyuwah.githubuserexplorer.core

sealed class Result<out T> {
    data class Success<T : Any>(val data: T) : Result<T>() {
        fun <R : Any> mapResult(mapper: (inData: T) -> R): Result<R> {
            return Success(mapper(data))
        }
    }

    data class Error(val message: String) : Result<Nothing>()
}

fun <T, O : Any> Result<T>.mapData(mapper: (inData: T) -> O): Result<O> {
    return when (this) {
        is Result.Success -> {
            this.mapResult {
                mapper(it)
            }
        }
        is Result.Error -> this
    }
}