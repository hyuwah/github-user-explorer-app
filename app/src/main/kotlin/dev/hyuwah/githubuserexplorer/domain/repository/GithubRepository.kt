package dev.hyuwah.githubuserexplorer.domain.repository

import dev.hyuwah.githubuserexplorer.core.Result
import dev.hyuwah.githubuserexplorer.data.remote.model.UserDetailResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserItemResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserRepoItemResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserSearchResultResponse
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun searchUser(query: String): Flow<Result<UserSearchResultResponse>>
    suspend fun getUserDetail(username: String): Flow<Result<UserDetailResponse>>
    suspend fun getUserRepos(username: String): Flow<Result<List<UserRepoItemResponse>>>
    suspend fun getUserFollowing(username: String): Flow<Result<List<UserItemResponse>>>
    suspend fun getUserFollowers(username: String): Flow<Result<List<UserItemResponse>>>
}