package com.msg.domain.usecase.post

import com.msg.data.repository.post.PostRepository
import com.msg.model.param.post.WritePostParam
import java.util.UUID
import javax.inject.Inject

class EditPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(id: UUID, body: WritePostParam) = runCatching {
        postRepository.editPost(
            id = id,
            body = body
        )
    }
}