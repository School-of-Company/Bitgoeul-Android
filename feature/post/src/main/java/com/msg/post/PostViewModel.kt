package com.msg.post

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.datastore.AuthTokenDataSource
import com.msg.domain.post.DeletePostUseCase
import com.msg.domain.post.EditPostUseCase
import com.msg.domain.post.GetDetailPostUseCase
import com.msg.domain.post.GetPostListUseCase
import com.msg.domain.post.SendPostUseCase
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.enumdatatype.FeedType
import com.msg.model.remote.model.post.PostModel
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
    private val sendPostUseCase: SendPostUseCase,
    private val authTokenDataSource: AuthTokenDataSource
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

    val role = Authority.valueOf(authTokenDataSource.getAuthority().toString())

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

    var links = mutableStateListOf<String>()
        private set

    var currentFeedType = mutableStateOf(FeedType.EMPLOYMENT)
        private set

    var savedTitle = mutableStateOf("")
        private set

    var savedContent = mutableStateOf("")
        private set

    var selectedId = mutableStateOf<UUID>(UUID.randomUUID())
        private set

    var isEditPage = mutableStateOf(false)
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
        ).onSuccess {
            it.catch { remoteError ->
                _editPostResponse.value = remoteError.errorHandling()
            }.collect {
                _editPostResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _editPostResponse.value = error.errorHandling()
        }
    }

    fun sendPost(
        title: String,
        content: String,
        feedType: FeedType,
    ) = viewModelScope.launch {
        sendPostUseCase(
            body = WritePostRequest(
                title = title,
                content = content,
                links = links,
                feedType = feedType
            )
        ).onSuccess {
            it.catch { remoteError ->
                _sendPostResponse.value = remoteError.errorHandling()
            }.collect {
                _sendPostResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _sendPostResponse.value = error.errorHandling()
        }
    }

    fun getPostList(
        type: FeedType
    ) = viewModelScope.launch {
        getPostListUseCase(
            page = 1,
            size = 10,
            type = type
        ).onSuccess {
            it.catch { remoteError ->
                _getPostListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getPostListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getPostListResponse.value = error.errorHandling()
        }
    }

    fun getDetailPost(
        id: UUID
    ) = viewModelScope.launch {
        getDetailPostUseCase(
            id = id
        ).onSuccess {
            it.catch { remoteError ->
                _getDetailPostResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getDetailPostResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getDetailPostResponse.value = error.errorHandling()
        }
    }

    fun addLinks() {
        links.add("")
    }

    fun saveLinkList() {
        links.forEachIndexed { index, link ->
            if (link == "") links.removeAt(index)
        }
    }

    fun getFilledEditPage() {
        savedTitle.value = detailPost.value.title
        savedContent.value = detailPost.value.content
        links.addAll(detailPost.value.links)
        isEditPage.value = true
    }
}