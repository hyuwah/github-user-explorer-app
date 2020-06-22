package dev.hyuwah.githubuserexplorer.presentation.repos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import dev.hyuwah.githubuserexplorer.R
import dev.hyuwah.githubuserexplorer.databinding.FragmentRepoListBinding
import dev.hyuwah.githubuserexplorer.presentation.utils.viewBinding

@AndroidEntryPoint
class RepoListFragment : Fragment(R.layout.fragment_repo_list) {

    private val binding by viewBinding(FragmentRepoListBinding::bind)
    private val viewModel by viewModels<RepoListViewModel>()
    private val repoListArgs by navArgs<RepoListFragmentArgs>()
    private val adapter = ReposAdapter(::onRepoItemClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()
        viewModel.loadRepo(repoListArgs.userName)
    }

    private fun setupRecyclerView() {
        binding.rvRepoList.apply {
            addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
            adapter = this@RepoListFragment.adapter
        }
    }

    private fun setupObserver() {
        viewModel.repoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun onRepoItemClick(item: RepoItemModel) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

}