package dev.hyuwah.githubuserexplorer.data.remote

import dev.hyuwah.githubuserexplorer.data.remote.model.UserSearchResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchServicesApi {

    @GET("search/users")
    suspend fun searchUser(@Query("q") query: String): Response<UserSearchResultResponse>

}