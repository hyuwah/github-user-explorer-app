package dev.hyuwah.githubuserexplorer.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import dagger.hilt.android.AndroidEntryPoint
import dev.hyuwah.githubuserexplorer.presentation.utils.viewBinding
import dev.hyuwah.githubuserexplorer.databinding.ActivityMainBinding
import dev.hyuwah.githubuserexplorer.presentation.utils.getQueryChangeFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: MainViewModel by viewModels()
    private val adapter = UsersAdapter {
        viewModel.getUserDetail(it.username)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        setupObserver()
        setupListener()
    }

    private fun setupRecyclerView() {
        binding.rvUserSearch.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.toast.observe(this){
            if(!it.isNullOrEmpty()) Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.userSearchResult.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun setupListener() {
        viewModel.bindSearchFlow(binding.searchView.getQueryChangeFlow())
    }
}