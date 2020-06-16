package dev.hyuwah.githubuserexplorer.data.remote.model


import com.google.gson.annotations.SerializedName

data class UserSearchResultResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean = false,
    @SerializedName("items")
    val items: List<UserItemResponse> = listOf(),
    @SerializedName("total_count")
    val totalCount: Int = 0
)