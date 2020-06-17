package dev.hyuwah.githubuserexplorer.presentation.users.detail

sealed class UserDetailEvent {
    data class ShareProfile(var url: String) : UserDetailEvent()
}
