package dev.hyuwah.githubuserexplorer.presentation.users.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.api.load
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.AndroidEntryPoint
import dev.hyuwah.githubuserexplorer.R
import dev.hyuwah.githubuserexplorer.data.remote.model.UserDetailResponse
import dev.hyuwah.githubuserexplorer.databinding.FragmentUserDetailBinding
import dev.hyuwah.githubuserexplorer.presentation.users.social.SocialType
import dev.hyuwah.githubuserexplorer.presentation.utils.NumberFormatter
import dev.hyuwah.githubuserexplorer.presentation.utils.observeEvent
import dev.hyuwah.githubuserexplorer.presentation.utils.setTextAndVisibility
import dev.hyuwah.githubuserexplorer.presentation.utils.viewBinding

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    private val binding by viewBinding(FragmentUserDetailBinding::bind)
    private val userDetailArgs by navArgs<UserDetailFragmentArgs>()
    private val viewModel by viewModels<UserDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupObserver()
        setupListener()
        viewModel.getUserDetail(userDetailArgs.userName)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_user_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                viewModel.shareProfile()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupObserver() {
        viewModel.userDetail.observe(viewLifecycleOwner, ::setupUI)
        viewModel.uiEvent.observeEvent(viewLifecycleOwner) {
            when (it) {
                is UserDetailEvent.ShareProfile -> shareProfile(it.url)
            }
        }
    }

    private fun shareProfile(url: String) {
        ShareCompat.IntentBuilder.from(requireActivity())
            .setType("text/plain")
            .setChooserTitle("Share Github Profile")
            .setText(url)
            .startChooser()
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
            tvRepoCount.text = userDetail.publicRepos.toString()
            tvFollowersCount.text = NumberFormatter.formatWithSuffix(userDetail.followers)
            tvFollowingCount.text = NumberFormatter.formatWithSuffix(userDetail.following)
        }
    }

    private fun setupListener() {
        with(binding) {
            btnRepo.setOnClickListener { }
            btnFollowers.setOnClickListener {
                navigateToSocialFragment(SocialType.Followers)
            }
            btnFollowing.setOnClickListener {
                navigateToSocialFragment(SocialType.Following)
            }
        }
    }

    private fun navigateToSocialFragment(type: SocialType) {
        findNavController().navigate(
            UserDetailFragmentDirections.actionUserDetailFragmentToUserSocialFragment(
                userDetailArgs.userName, type
            )
        )
    }

    private fun getFollowersFollowing(followers: Int, following: Int): String {
        val followersStr = NumberFormatter.formatWithSuffix(followers)
        val followingStr = NumberFormatter.formatWithSuffix(following)
        return "$followersStr followers ▪ $followingStr following"
    }
}