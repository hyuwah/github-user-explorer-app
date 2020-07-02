package dev.hyuwah.githubuserexplorer.domain.usecase

import dev.hyuwah.githubuserexplorer.core.Result
import dev.hyuwah.githubuserexplorer.core.mapData
import dev.hyuwah.githubuserexplorer.domain.repository.GithubRepository
import dev.hyuwah.githubuserexplorer.presentation.model.RepoItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

class GetUserRepos @Inject constructor(
    private val repo: GithubRepository
) : UseCase<List<RepoItemModel>, String>() {
    override suspend fun buildUseCase(params: String): Flow<Result<List<RepoItemModel>>> {
        return repo.getUserRepos(params).map {
            it.mapData { data ->
                data.map {
                    RepoItemModel(
                        it.id,
                        it.htmlUrl,
                        it.name,
                        it.description.orEmpty(),
                        it.language.orEmpty(),
                        it.stargazersCount,
                        it.forksCount,
                        it.fork,
                        it.owner.login,
                        it.owner.avatarUrl,
                        LocalDateTime.ofInstant(
                            Instant.parse(it.createdAt),
                            ZoneId.systemDefault()
                        ),
                        LocalDateTime.ofInstant(
                            Instant.parse(it.updatedAt),
                            ZoneId.systemDefault()
                        )
                    )
                }
            }
        }
    }
}