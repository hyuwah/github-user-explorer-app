package dev.hyuwah.githubuserexplorer.presentation.search

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dev.hyuwah.githubuserexplorer.core.Result
import dev.hyuwah.githubuserexplorer.domain.usecase.SearchUser
import dev.hyuwah.githubuserexplorer.presentation.model.UserItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
class UserSearchViewModel @ViewModelInject constructor(
    private val searchUser: SearchUser,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _userSearchResult =
        MutableLiveData<List<UserItemModel>>(emptyList())
    val userSearchResult = _userSearchResult as LiveData<List<UserItemModel>>

    fun bindSearchFlow(queryFlow: StateFlow<String>) {
        viewModelScope.launch {
            queryFlow.debounce(500)
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .flowOn(Dispatchers.Main)
                .flatMapLatest { query ->
                    searchUser.execute(query)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _userSearchResult.postValue(result.data)
                        }
                        is Result.Error -> {
                            Log.d("LOG", result.message)
                        }
                    }
                }
        }
    }

}