package dev.hyuwah.githubuserexplorer.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dev.hyuwah.githubuserexplorer.data.remote.model.UserItemResponse
import dev.hyuwah.githubuserexplorer.data.remote.model.UserSearchResultResponse
import dev.hyuwah.githubuserexplorer.domain.repository.GithubRepository
import dev.hyuwah.githubuserexplorer.presentation.model.UserItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: GithubRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _userSearchResult =
        MutableLiveData<List<UserItemModel>>(emptyList())
    val userSearchResult = _userSearchResult as LiveData<List<UserItemModel>>

    private val _toast = MutableLiveData<String>()
    val toast = _toast as LiveData<String>

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun bindSearchFlow(queryFlow: StateFlow<String>) {
        viewModelScope.launch {
            queryFlow.debounce(500)
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .flowOn(Dispatchers.Main)
                .collect { query ->
                    val result = repository.searchUser(query)
                    if (result.isSuccessful) {
                        val listUser = result.body()?.items.orEmpty().map {
                            UserItemModel(it.id, it.login, it.avatarUrl)
                        }
                        _userSearchResult.postValue(listUser)
                    }
                }
        }
    }

    fun getUserDetail(userName: String) {
        viewModelScope.launch {
            val result = repository.getUserDetail(userName)
            if (result.isSuccessful) {
                _toast.postValue(result.body()?.bio.orEmpty())
            }
        }
    }

}