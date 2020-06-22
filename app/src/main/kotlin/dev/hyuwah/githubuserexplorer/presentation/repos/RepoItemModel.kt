package dev.hyuwah.githubuserexplorer.presentation.repos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class RepoItemModel(
    var id: Int,
    var url: String,
    var name: String,
    var description: String,
    var language: String,
    var starCount: Int,
    var forkCount: Int,
    var isFork: Boolean,
    var ownerUsername: String,
    var ownerAvatar: String,
    var createdDate: LocalDateTime,
    var updatedDate: LocalDateTime
) : Parcelable