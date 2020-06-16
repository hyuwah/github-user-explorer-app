package dev.hyuwah.githubuserexplorer.domain.repository

import dev.hyuwah.githubuserexplorer.data.remote.model.UserDetailResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserItemResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserRepoItemResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserSearchResultResponse
import retrofit2.Response

interface GithubRepository {
    suspend fun searchUser(query: String): Response<UserSearchResultResponse>
    suspend fun getUserDetail(username: String): Response<UserDetailResponse>
    suspend fun getUserRepos(username: String): Response<List<UserRepoItemResponse>>
    suspend fun getUserFollowing(username: String): Response<List<UserItemResponse>>
    suspend fun getUserFollowers(username: String): Response<List<UserItemResponse>>
}