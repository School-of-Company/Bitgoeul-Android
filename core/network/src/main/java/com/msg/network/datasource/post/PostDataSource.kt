package com.msg.network.datasource.post

import com.msg.model.enumdata.FeedType
import com.msg.network.request.post.WritePostRequest
import com.msg.network.response.post.GetDetailPostResponse
import com.msg.network.response.post.GetPostListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface PostDataSource {
    fun sendPost(body: WritePostRequest): Flow<Unit>
    fun getPostList(type: FeedType, size: Int, page: Int): Flow<GetPostListResponse>
    fun getDetailPost(id: UUID): Flow<GetDetailPostResponse>
    fun editPost(id: UUID, body: WritePostRequest): Flow<Unit>
    fun deletePost(id: UUID): Flow<Unit>
}