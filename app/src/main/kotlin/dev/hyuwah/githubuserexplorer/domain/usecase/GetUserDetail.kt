package dev.hyuwah.githubuserexplorer.domain.usecase

import dev.hyuwah.githubuserexplorer.core.Result
import dev.hyuwah.githubuserexplorer.data.remote.model.UserDetailResponse
import dev.hyuwah.githubuserexplorer.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetail @Inject constructor(
    private val repo: GithubRepository
) : UseCase<UserDetailResponse, String>() {
    override suspend fun buildUseCase(params: String): Flow<Result<UserDetailResponse>> {
        return repo.getUserDetail(params)
    }
}