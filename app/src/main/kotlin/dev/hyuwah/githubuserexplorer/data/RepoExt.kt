package dev.hyuwah.githubuserexplorer.data

import dev.hyuwah.githubuserexplorer.core.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T : Any> Response<T>.toDomainFlow(): Flow<Result<T>> {
    return flow {
        val apiCall = this@toDomainFlow
        if (apiCall.isSuccessful && apiCall.body() != null) {
            emit(Result.Success(apiCall.body()!!))
        } else {
            emit(Result.Error(apiCall.message()))
        }
    }
}