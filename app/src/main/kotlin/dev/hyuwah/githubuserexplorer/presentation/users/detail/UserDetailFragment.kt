package dev.hyuwah.githubuserexplorer.presentation.users.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import coil.api.load
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.AndroidEntryPoint
import dev.hyuwah.githubuserexplorer.R
import dev.hyuwah.githubuserexplorer.data.remote.model.UserDetailResponse
import dev.hyuwah.githubuserexplorer.databinding.FragmentUserDetailBinding
import dev.hyuwah.githubuserexplorer.presentation.utils.NumberFormatter
import dev.hyuwah.githubuserexplorer.presentation.utils.setTextAndVisibility
import dev.hyuwah.githubuserexplorer.presentation.utils.viewBinding

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    private val binding by viewBinding(FragmentUserDetailBinding::bind)
    private val userDetailArgs by navArgs<UserDetailFragmentArgs>()
    private val viewModel by viewModels<UserDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.getUserDetail(userDetailArgs.userName)
    }

    private fun setupObserver() {
        viewModel.userDetail.observe(viewLifecycleOwner, ::setupUI)
    }

    private fun setupUI(userDetail: UserDetailResponse) {
        with(binding) {
            tvName.setTextAndVisibility(userDetail.name)
            tvUserName.text = userDetail.login
            ivUserPic.load(userDetail.avatarUrl) {
                crossfade(true)
                transformations(RoundedCornersTransformation(20f))
            }
            tvBio.setTextAndVisibility(userDetail.bio)
            tvCompany.setTextAndVisibility(userDetail.company)
            tvLocation.setTextAndVisibility(userDetail.location)
            tvBlog.setTextAndVisibility(userDetail.blog)
            tvFollowersFollowing.setTextAndVisibility(
                getFollowersFollowing(
                    userDetail.followers,
                    userDetail.following
                )
            )
        }
    }

    private fun getFollowersFollowing(followers: Int, following: Int): String {
        val followersStr = NumberFormatter.formatWithSuffix(followers)
        val followingStr = NumberFormatter.formatWithSuffix(following)
        return "$followersStr followers ▪ $followingStr following"
    }
}