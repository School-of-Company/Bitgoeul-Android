package com.msg.post

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.post.DeletePostUseCase
import com.msg.domain.post.EditPostUseCase
import com.msg.domain.post.GetDetailPostUseCase
import com.msg.domain.post.GetPostListUseCase
import com.msg.domain.post.SendPostUseCase
import com.msg.model.remote.enumdatatype.FeedType
import com.msg.model.remote.request.post.WritePostRequest
import com.msg.model.remote.response.post.GetDetailPostResponse
import com.msg.model.remote.response.post.GetPostListResponse
import com.msg.post.util.Event
import com.msg.post.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val deletePostUseCase: DeletePostUseCase,
    private val editPostUseCase: EditPostUseCase,
    private val getDetailPostUseCase: GetDetailPostUseCase,
    private val getPostListUseCase: GetPostListUseCase,
    private val sendPostUseCase: SendPostUseCase
) : ViewModel() {

    private val _deletePostResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val deletePostResponse = _deletePostResponse.asStateFlow()

    private val _editPostResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val editPostResponse = _editPostResponse.asStateFlow()

    private val _getDetailPostResponse = MutableStateFlow<Event<GetDetailPostResponse>>(Event.Loading)
    val getDetailPostResponse = _getDetailPostResponse.asStateFlow()

    private val _getPostListResponse = MutableStateFlow<Event<GetPostListResponse>>(Event.Loading)
    val getPostListResponse = _getPostListResponse.asStateFlow()

    private val _sendPostResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val sendPostResponse = _sendPostResponse.asStateFlow()

    var detailPost = mutableStateOf(
        GetDetailPostResponse(
            title = "",
            writer = "",
            content = "",
            feedType = FeedType.NOTICE,
            modifiedAt = LocalDateTime.now(),
            links = listOf()
        )
    )
        private set

    var postList = mutableStateOf(
        GetPostListResponse(
            posts = listOf()
        )
    )
        private set

    fun deletePost(
        id: UUID
    ) = viewModelScope.launch {
        deletePostUseCase(id = id).onSuccess {
            it.catch { remoteError ->
                _deletePostResponse.value = remoteError.errorHandling()
            }.collect {
                _deletePostResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _deletePostResponse.value = error.errorHandling()
        }
    }

    fun editPost(
        id: UUID,
        title: String,
        content: String,
        links: List<String>,
        feedType: FeedType
    ) = viewModelScope.launch {
        editPostUseCase(
            id = id,
            body = WritePostRequest(
                title = title,
                content = content,
                links = links,
                feedType = feedType
            )
        )
    }
}