package dev.hyuwah.githubuserexplorer.data.repository

import dev.hyuwah.githubuserexplorer.data.remote.SearchServicesApi
import dev.hyuwah.githubuserexplorer.data.remote.UserServicesApi
import dev.hyuwah.githubuserexplorer.data.remote.model.UserDetailResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserItemResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserRepoItemResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserSearchResultResponse
import dev.hyuwah.githubuserexplorer.domain.repository.GithubRepository
import retrofit2.Response
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val searchService: SearchServicesApi,
    private val userService: UserServicesApi
): GithubRepository {

    override suspend fun searchUser(query: String): Response<UserSearchResultResponse> {
        return searchService.searchUser(query)
    }

    override suspend fun getUserDetail(username: String): Response<UserDetailResponse> {
        return userService.getDetail(username)
    }

    override suspend fun getUserRepos(username: String): Response<List<UserRepoItemResponse>> {
        return userService.getRepos(username)
    }

    override suspend fun getUserFollowing(username: String): Response<List<UserItemResponse>> {
        return userService.getFollowing(username)
    }

    override suspend fun getUserFollowers(username: String): Response<List<UserItemResponse>> {
        return userService.getFollowers(username)
    }

}