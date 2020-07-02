package dev.hyuwah.githubuserexplorer.data.repository

import dev.hyuwah.githubuserexplorer.core.Result
import dev.hyuwah.githubuserexplorer.data.remote.SearchServicesApi
import dev.hyuwah.githubuserexplorer.data.remote.UserServicesApi
import dev.hyuwah.githubuserexplorer.data.remote.model.UserDetailResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserItemResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserRepoItemResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserSearchResultResponse
import dev.hyuwah.githubuserexplorer.data.toDomainFlow
import dev.hyuwah.githubuserexplorer.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val searchService: SearchServicesApi,
    private val userService: UserServicesApi
): GithubRepository {

    override suspend fun searchUser(query: String): Flow<Result<UserSearchResultResponse>> {
        return searchService.searchUser(query).toDomainFlow()
    }

    override suspend fun getUserDetail(username: String): Flow<Result<UserDetailResponse>> {
        return userService.getDetail(username).toDomainFlow()
    }

    override suspend fun getUserRepos(username: String): Flow<Result<List<UserRepoItemResponse>>> {
        return userService.getRepos(username).toDomainFlow()
    }

    override suspend fun getUserFollowing(username: String): Flow<Result<List<UserItemResponse>>> {
        return userService.getFollowing(username).toDomainFlow()
    }

    override suspend fun getUserFollowers(username: String): Flow<Result<List<UserItemResponse>>> {
        return userService.getFollowers(username).toDomainFlow()
    }

}