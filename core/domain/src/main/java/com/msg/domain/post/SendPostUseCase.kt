package com.msg.domain.post

import com.msg.data.repository.post.PostRepository
import com.msg.model.remote.request.post.WritePostRequest
import javax.inject.Inject

class SendPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(body: WritePostRequest) = runCatching {
        postRepository.sendPost(body = body)
    }
}