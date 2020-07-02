package dev.hyuwah.githubuserexplorer.presentation.social

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.githubuserexplorer.domain.usecase.GetUserFollowers
import dev.hyuwah.githubuserexplorer.domain.usecase.GetUserFollowing
import dev.hyuwah.githubuserexplorer.presentation.model.UserItemModel
import kotlinx.coroutines.launch

class UserSocialViewModel @ViewModelInject constructor(
    private val getUserFollowers: GetUserFollowers,
    private val getUserFollowing: GetUserFollowing
) : ViewModel() {

    private val _userList =
        MutableLiveData<List<UserItemModel>>(emptyList())
    val userList = _userList as LiveData<List<UserItemModel>>

    fun loadData(userName: String, socialType: SocialType) {
        viewModelScope.launch {
            when (socialType) {
                SocialType.Followers -> {
                    getUserFollowers.execute(userName,
                        onSuccess = {
                            _userList.postValue(it)
                        }
                    )
                }
                SocialType.Following -> {
                    getUserFollowing.execute(userName,
                        onSuccess = {
                            _userList.postValue(it)
                        }
                    )
                }
            }
        }
    }

}