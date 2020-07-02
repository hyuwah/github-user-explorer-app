package dev.hyuwah.githubuserexplorer.presentation.repos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.githubuserexplorer.domain.usecase.GetUserRepos
import dev.hyuwah.githubuserexplorer.presentation.model.RepoItemModel
import kotlinx.coroutines.launch

class RepoListViewModel @ViewModelInject constructor(
    private val getUserRepos: GetUserRepos
) : ViewModel() {

    private val _repoList = MutableLiveData<List<RepoItemModel>>(emptyList())
    val repoList = _repoList as LiveData<List<RepoItemModel>>

    fun loadRepo(userName: String) {
        viewModelScope.launch {
            getUserRepos.execute(userName,
                onSuccess = {
                    _repoList.postValue(it.sortedByDescending { it.updatedDate })

                })
        }
    }

}