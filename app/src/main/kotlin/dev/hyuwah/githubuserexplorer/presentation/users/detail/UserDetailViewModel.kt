package dev.hyuwah.githubuserexplorer.presentation.users.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dev.hyuwah.githubuserexplorer.data.remote.model.UserDetailResponse
import dev.hyuwah.githubuserexplorer.domain.repository.GithubRepository
import kotlinx.coroutines.launch

class UserDetailViewModel @ViewModelInject constructor(
    private val repo: GithubRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _userDetail = MutableLiveData<UserDetailResponse>()
    val userDetail = _userDetail as LiveData<UserDetailResponse>

    fun getUserDetail(userName: String) {
        viewModelScope.launch {
            val result = repo.getUserDetail(userName)
            if (result.isSuccessful) {
                result.body()?.let {
                    _userDetail.postValue(it)
                }
            }
        }
    }

}