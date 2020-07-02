package dev.hyuwah.githubuserexplorer.presentation.social

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.hyuwah.githubuserexplorer.R
import dev.hyuwah.githubuserexplorer.databinding.FragmentUserSocialBinding
import dev.hyuwah.githubuserexplorer.presentation.model.UserItemModel
import dev.hyuwah.githubuserexplorer.presentation.search.UsersAdapter
import dev.hyuwah.githubuserexplorer.presentation.utils.setTitle
import dev.hyuwah.githubuserexplorer.presentation.utils.viewBinding

@AndroidEntryPoint
class UserSocialFragment : Fragment(R.layout.fragment_user_social) {

    private val binding by viewBinding(FragmentUserSocialBinding::bind)
    private val viewModel by viewModels<UserSocialViewModel>()
    private val userSocialArgs by navArgs<UserSocialFragmentArgs>()
    private val adapter = UsersAdapter(::onUserItemClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle("${userSocialArgs.userName}'s ${userSocialArgs.socialType.name}")
        setupRecyclerView()
        setupObserver()
        viewModel.loadData(userSocialArgs.userName, userSocialArgs.socialType)
    }

    private fun setupRecyclerView() {
        binding.rvUserSocial.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.userList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun onUserItemClick(user: UserItemModel) {
        findNavController().navigate(
            UserSocialFragmentDirections.actionUserSocialFragmentToUserDetailFragment(
                user.username
            )
        )
    }

}