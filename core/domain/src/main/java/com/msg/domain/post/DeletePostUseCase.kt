package com.msg.domain.post

import com.msg.data.repository.post.PostRepository
import java.util.UUID
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(id: UUID) = runCatching {
        postRepository.deletePost(id = id)
    }
}