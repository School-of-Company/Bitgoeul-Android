package com.msg.domain.usecase.post

import com.msg.data.repository.post.PostRepository
import com.msg.model.param.post.WritePostParam
import javax.inject.Inject

class SendPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(body: WritePostParam) = runCatching {
        postRepository.sendPost(body = body)
    }
}