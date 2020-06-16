package dev.hyuwah.githubuserexplorer.data.remote

import dev.hyuwah.githubuserexplorer.data.remote.model.UserDetailResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserItemResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserRepoItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface UserServicesApi {

    @GET("/users/{user}/following")
    suspend fun getFollowing(@Path("user") user: String): Response<List<UserItemResponse>>

    @GET("/users/{user}/followers")
    suspend fun getFollowers(@Path("user") user: String): Response<List<UserItemResponse>>

    @GET("/users/{user}/repos")
    suspend fun getRepos(@Path("user") user: String): Response<List<UserRepoItemResponse>>

    @GET("/users/{user}")
    suspend fun getDetail(@Path("user") user: String): Response<UserDetailResponse>

}