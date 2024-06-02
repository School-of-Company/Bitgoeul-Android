package com.msg.data.repository.post

import com.msg.model.remote.enumdatatype.FeedType
import com.msg.model.remote.request.post.WritePostRequest
import com.msg.model.remote.response.post.GetDetailPostResponse
import com.msg.model.remote.response.post.GetPostListResponse
import com.msg.network.datasource.post.PostDataSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSource: PostDataSource
) : PostRepository {
    override fun sendPost(body: WritePostRequest): Flow<Unit> {
        return postDataSource.sendPost(body = body)
    }

    override fun getPostList(
        type: FeedType,
        size: Int,
        page: Int
    ): Flow<GetPostListResponse> {
        return postDataSource.getPostList(
            type = type,
            page = page,
            size = size
        )
    }

    override fun getDetailPost(id: UUID): Flow<GetDetailPostResponse> {
        return postDataSource.getDetailPost(id = id)
    }

    override fun editPost(id: UUID, body: WritePostRequest): Flow<Unit> {
        return postDataSource.editPost(
            id = id,
            body = body
        )
    }

    override fun deletePost(id: UUID): Flow<Unit> {
        return postDataSource.deletePost(id = id)
    }
}