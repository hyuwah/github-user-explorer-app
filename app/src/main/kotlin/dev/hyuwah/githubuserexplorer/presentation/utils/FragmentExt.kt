package dev.hyuwah.githubuserexplorer.presentation.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.setTitle(title: String) {
    (requireActivity() as AppCompatActivity)?.supportActionBar?.title = title
}