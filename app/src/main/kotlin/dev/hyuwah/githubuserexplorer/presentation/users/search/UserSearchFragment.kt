package dev.hyuwah.githubuserexplorer.presentation.users.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.hyuwah.githubuserexplorer.R
import dev.hyuwah.githubuserexplorer.databinding.FragmentUserSearchBinding
import dev.hyuwah.githubuserexplorer.presentation.model.UserItemModel
import dev.hyuwah.githubuserexplorer.presentation.utils.getQueryChangeFlow
import dev.hyuwah.githubuserexplorer.presentation.utils.viewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class UserSearchFragment : Fragment(R.layout.fragment_user_search) {

    private val binding by viewBinding(FragmentUserSearchBinding::bind)
    private val viewModel by viewModels<UserSearchViewModel>()
    private val adapter = UsersAdapter(::onUserItemClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()
        setupListener()
    }

    private fun setupRecyclerView() {
        binding.rvUserSearch.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.userSearchResult.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupListener() {
        viewModel.bindSearchFlow(binding.searchView.getQueryChangeFlow())
    }

    private fun onUserItemClick(user: UserItemModel) {
        findNavController().navigate(
            UserSearchFragmentDirections.actionUserSearchFragmentToUserDetailFragment(
                user.username
            )
        )
    }
}