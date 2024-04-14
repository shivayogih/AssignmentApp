package com.shivayogi.assignmentapp.ui.posts


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.shivayogi.assignmentapp.data.responses.PostItem
import com.shivayogi.assignmentapp.repository.AssignmentRepository
import com.shivayogi.assignmentapp.util.Constants
import com.shivayogi.assignmentapp.util.DefaultPaginator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostListViewModel @Inject constructor(
    private val repository: AssignmentRepository
) : ViewModel() {


    var state by mutableStateOf(ScreenState())

    private val paginator = DefaultPaginator(initialKey = state.page, onLoadUpdated = {
        state = state.copy(isLoading = it)
    }, onRequest = { nextPage ->
        repository.getUserPostList(nextPage, Constants.PAGE_SIZE)
    }, getNextKey = {
        state.page + 1
    }, onError = {
        state = state.copy(error = it?.localizedMessage)
    }, onSuccess = { items, newKey ->
        state = state.copy(
            items = state.items + items, page = newKey, endReached = items.isEmpty()
        )
    })


    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<PostItem> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)