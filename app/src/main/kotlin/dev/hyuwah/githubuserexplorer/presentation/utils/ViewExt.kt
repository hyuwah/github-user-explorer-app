package dev.hyuwah.githubuserexplorer.presentation.utils

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
fun TextInputLayout.getTextChangeFlow(): StateFlow<String> {
    val query = MutableStateFlow("")
    editText?.doOnTextChanged { text, start, before, count ->
        text?.let {
            query.value = it.toString()
        }
    }
    return query
}

@ExperimentalCoroutinesApi
fun SearchView.getQueryChangeFlow(): StateFlow<String> {
    val query = MutableStateFlow("")
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let {
                query.value = it
                return true
            }
            return false
        }

    })
    return query
}

fun TextView.setTextAndVisibility(text: String?) {
    visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
    text?.let { this.text = it }
}