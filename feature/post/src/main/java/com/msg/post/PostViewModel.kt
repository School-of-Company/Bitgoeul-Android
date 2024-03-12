package com.msg.post

import androidx.lifecycle.ViewModel
import com.msg.domain.post.DeletePostUseCase
import com.msg.domain.post.EditPostUseCase
import com.msg.domain.post.GetDetailPostUseCase
import com.msg.domain.post.GetPostListUseCase
import com.msg.domain.post.SendPostUseCase
import com.msg.post.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val deletePostUseCase: DeletePostUseCase,
    private val editPostUseCase: EditPostUseCase,
    private val getDetailPostUseCase: GetDetailPostUseCase,
    private val getPostListUseCase: GetPostListUseCase,
    private val sendPostUseCase: SendPostUseCase
) : ViewModel() {

}