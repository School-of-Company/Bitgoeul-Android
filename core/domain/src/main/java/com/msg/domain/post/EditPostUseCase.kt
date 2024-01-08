package com.msg.domain.post

import com.msg.data.repository.post.PostRepository
import com.msg.model.remote.request.post.WritePostRequest
import java.util.UUID
import javax.inject.Inject

class EditPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(id: UUID, body: WritePostRequest) = runCatching {
        postRepository.editPost(
            id = id,
            body = body
        )
    }
}