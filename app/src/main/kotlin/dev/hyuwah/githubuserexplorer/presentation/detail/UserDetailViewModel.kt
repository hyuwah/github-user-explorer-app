package dev.hyuwah.githubuserexplorer.presentation.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dev.hyuwah.githubuserexplorer.data.remote.model.UserDetailResponse
import dev.hyuwah.githubuserexplorer.domain.usecase.GetUserDetail
import dev.hyuwah.githubuserexplorer.presentation.utils.LiveEvent
import dev.hyuwah.githubuserexplorer.presentation.utils.MutableLiveEvent
import dev.hyuwah.githubuserexplorer.presentation.utils.postEvent
import kotlinx.coroutines.launch

class UserDetailViewModel @ViewModelInject constructor(
    private val getUserDetail: GetUserDetail,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _userDetail = MutableLiveData<UserDetailResponse>()
    val userDetail = _userDetail as LiveData<UserDetailResponse>

    private val _uiEvent = MutableLiveEvent<UserDetailEvent>()
    val uiEvent = _uiEvent as LiveEvent<UserDetailEvent>

    fun getUserDetail(userName: String) {
        viewModelScope.launch {
            getUserDetail.execute(
                userName,
                onSuccess = {
                    _userDetail.postValue(it)
                }
            )
        }
    }

    fun shareProfile() {
        _userDetail.value?.let {
            _uiEvent.postEvent(UserDetailEvent.ShareProfile(it.htmlUrl))
        }
    }

}