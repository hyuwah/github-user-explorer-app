package dev.hyuwah.githubuserexplorer.presentation.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import dev.hyuwah.githubuserexplorer.databinding.RowItemRepoBinding
import dev.hyuwah.githubuserexplorer.presentation.utils.NumberFormatter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

typealias RepoItemClick = (item: RepoItemModel) -> Unit

class ReposAdapter(
    private val onItemClick: RepoItemClick
) : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RepoItemModel>() {
            override fun areItemsTheSame(oldItem: RepoItemModel, newItem: RepoItemModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RepoItemModel,
                newItem: RepoItemModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    private val differ = AsyncListDiffer(
        this,
        diffCallback
    )

    fun submitList(newList: List<RepoItemModel>) {
        differ.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RowItemRepoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position], onItemClick)
    }

    inner class ViewHolder(private val binding: RowItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepoItemModel, onItemClick: RepoItemClick) = with(binding) {
            root.setOnClickListener { onItemClick(item) }
            ivUserPic.load(item.ownerAvatar) {
                crossfade(true)
                transformations(RoundedCornersTransformation(8f))
            }
            tvUserName.text = item.ownerUsername
            tvRepoName.text = item.name
            tvRepoDesc.text = item.description
            tvRepoDesc.setVisibleIf(item.description.isNotEmpty())
            tvRepoStarCount.text = NumberFormatter.formatWithSuffix(item.starCount)
            tvRepoForkCount.text = NumberFormatter.formatWithSuffix(item.forkCount)
            tvRepoLanguage.text = item.language
            tvRepoLanguage.setVisibleIf(item.language.isNotEmpty())
            tvRepoDateCreated.text = "Created: ${item.createdDate.parseToString()}"
            tvRepoDateUpdated.text = "Updated: ${item.updatedDate.parseToString()}"
        }

        private fun LocalDateTime.parseToString(): String {
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm")
            return formatter.format(this)
        }

        private fun View.setVisibleIf(predicate: Boolean) {
            visibility = if (predicate) View.VISIBLE else View.GONE
        }

    }

}