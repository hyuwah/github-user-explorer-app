package dev.hyuwah.githubuserexplorer.presentation.detail

sealed class UserDetailEvent {
    data class ShareProfile(var url: String) : UserDetailEvent()
}
