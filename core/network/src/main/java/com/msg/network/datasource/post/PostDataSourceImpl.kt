package com.msg.network.datasource.post

import com.msg.network.response.post.*
import com.msg.network.request.post.WritePostRequest
import com.msg.network.api.PostAPI
import com.msg.model.enumdata.FeedType
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class PostDataSourceImpl @Inject constructor(
    private val postAPI: PostAPI
) : PostDataSource {
    override fun sendPost(body: WritePostRequest): Flow<Unit> =
        makeRequest { postAPI.sendPost(body = body) }

    override fun getPostList(type: FeedType, size: Int, page: Int): Flow<GetPostListResponse> =
        makeRequest { postAPI.getPostList(type = type, size = size, page = page) }

    override fun getDetailPost(id: UUID): Flow<GetDetailPostResponse> =
        makeRequest { postAPI.getDetailPost(id = id) }

    override fun editPost(id: UUID, body: WritePostRequest): Flow<Unit> =
        makeRequest { postAPI.editPost(id = id, body = body) }

    override fun deletePost(id: UUID): Flow<Unit> =
        makeRequest { postAPI.deletePost(id = id) }
}