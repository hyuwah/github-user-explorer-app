package dev.hyuwah.githubuserexplorer.presentation.repos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.githubuserexplorer.domain.repository.GithubRepository
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class RepoListViewModel @ViewModelInject constructor(
    private val repo: GithubRepository
) : ViewModel() {

    private val _repoList = MutableLiveData<List<RepoItemModel>>(emptyList())
    val repoList = _repoList as LiveData<List<RepoItemModel>>

    fun loadRepo(userName: String) {
        viewModelScope.launch {
            val result = repo.getUserRepos(userName)
            if (result.isSuccessful) {
                val listRepo = result.body().orEmpty().map {
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
                        LocalDateTime.ofInstant(Instant.parse(it.updatedAt), ZoneId.systemDefault())
                    )
                }
                _repoList.postValue(listRepo.sortedByDescending { it.updatedDate })
            }
        }
    }

}