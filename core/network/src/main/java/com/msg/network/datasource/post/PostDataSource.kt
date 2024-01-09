package com.msg.network.datasource.post

import com.msg.model.remote.enumdatatype.FeedType
import com.msg.model.remote.request.post.WritePostRequest
import com.msg.model.remote.response.post.GetDetailPostResponse
import com.msg.model.remote.response.post.GetPostListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface PostDataSource {
    suspend fun sendPost(body: WritePostRequest): Flow<Unit>
    suspend fun getPostList(type: FeedType, size: Int, page: Int): Flow<GetPostListResponse>
    suspend fun getDetailPost(id: UUID): Flow<GetDetailPostResponse>
    suspend fun editPost(id: UUID, body: WritePostRequest): Flow<Unit>
    suspend fun deletePost(id: UUID): Flow<Unit>
}