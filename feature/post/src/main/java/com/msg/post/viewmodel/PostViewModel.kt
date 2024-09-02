package com.msg.post.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.common.errorhandling.errorHandling
import com.msg.common.event.Event
import com.msg.domain.usecase.auth.GetAuthorityUseCase
import com.msg.domain.usecase.post.*
import com.msg.model.entity.post.GetDetailPostEntity
import com.msg.model.entity.post.GetPostListEntity
import com.msg.model.enumdata.FeedType
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
    private val getDetailPostUseCase: GetDetailPostUseCase,
    private val getPostListUseCase: GetPostListUseCase,
) : ViewModel() {

    private val _getDetailPostResponse = MutableStateFlow<Event<GetDetailPostEntity>>(Event.Loading)
    val getDetailPostResponse = _getDetailPostResponse.asStateFlow()

    private val _getPostListResponse = MutableStateFlow<Event<GetPostListEntity>>(Event.Loading)
    val getPostListResponse = _getPostListResponse.asStateFlow()

    var detailPost = mutableStateOf(
        GetDetailPostEntity(
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
        GetPostListEntity(posts = listOf())
    )
        private set

    var currentFeedType = mutableStateOf(FeedType.EMPLOYMENT)
        private set

    var selectedId = mutableStateOf<UUID>(UUID.randomUUID())
        private set

    internal fun getPostList(
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

    internal fun getDetailPost(
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

    internal fun clearPostList() { postList.value = GetPostListEntity(posts = emptyList()) }
}