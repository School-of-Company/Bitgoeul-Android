package com.msg.domain.usecase.post

import com.msg.data.repository.post.PostRepository
import java.util.UUID
import javax.inject.Inject

class GetDetailPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(id: UUID) = runCatching {
        postRepository.getDetailPost(id = id)
    }
}