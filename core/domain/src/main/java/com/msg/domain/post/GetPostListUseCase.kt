package com.msg.domain.post

import com.msg.data.repository.post.PostRepository
import com.msg.model.remote.enumdatatype.FeedType
import javax.inject.Inject

class GetPostListUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(
        type: FeedType,
        size: Int,
        page: Int
    ) = runCatching {
        postRepository.getPostList(
            type = type,
            size = size,
            page = page
        )
    }
}