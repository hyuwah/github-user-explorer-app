package dev.hyuwah.githubuserexplorer.domain.usecase

import dev.hyuwah.githubuserexplorer.core.Result
import dev.hyuwah.githubuserexplorer.core.mapData
import dev.hyuwah.githubuserexplorer.domain.repository.GithubRepository
import dev.hyuwah.githubuserexplorer.presentation.model.UserItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUser @Inject constructor(
    private val repo: GithubRepository
) : UseCase<List<UserItemModel>, String>() {

    override suspend fun buildUseCase(params: String): Flow<Result<List<UserItemModel>>> {
        return repo.searchUser(params).map {
            it.mapData { data ->
                data.items.map {
                    UserItemModel(it.id, it.login, it.avatarUrl)
                }
            }
        }
    }

}