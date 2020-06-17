package dev.hyuwah.githubuserexplorer.presentation.users.social

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.githubuserexplorer.domain.repository.GithubRepository
import dev.hyuwah.githubuserexplorer.presentation.model.UserItemModel
import kotlinx.coroutines.launch

class UserSocialViewModel @ViewModelInject constructor(
    private val repo: GithubRepository
) : ViewModel() {

    private val _userList =
        MutableLiveData<List<UserItemModel>>(emptyList())
    val userList = _userList as LiveData<List<UserItemModel>>

    fun loadData(userName: String, socialType: SocialType) {
        viewModelScope.launch {
            val result = when (socialType) {
                SocialType.Followers -> repo.getUserFollowers(userName)
                SocialType.Following -> repo.getUserFollowing(userName)
            }
            if (result.isSuccessful) {
                val listUser = result.body().orEmpty().map {
                    UserItemModel(it.id, it.login, it.avatarUrl)
                }
                _userList.postValue(listUser)
            }
        }
    }

}