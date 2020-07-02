package dev.hyuwah.githubuserexplorer.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import dev.hyuwah.githubuserexplorer.databinding.RowItemUserBinding
import dev.hyuwah.githubuserexplorer.presentation.model.UserItemModel

typealias UserItemClick = (item: UserItemModel) -> Unit

class UsersAdapter(
    private val onItemClick: UserItemClick
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    companion object {
        private val diffCallback =
            object : DiffUtil.ItemCallback<UserItemModel>() {
                override fun areItemsTheSame(
                    oldItem: UserItemModel,
                    newItem: UserItemModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: UserItemModel,
                    newItem: UserItemModel
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    private val differ = AsyncListDiffer(
        this,
        diffCallback
    )

    fun submitList(newList: List<UserItemModel>) {
        differ.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RowItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position], onItemClick)
    }

    inner class ViewHolder(
        private val binding: RowItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserItemModel, onItemClick: UserItemClick) = with(binding) {
            root.setOnClickListener { onItemClick(item) }
            ivUserPic.load(item.avatarUrl) {
                crossfade(true)
            }
            tvUserName.text = item.username
        }

    }
}