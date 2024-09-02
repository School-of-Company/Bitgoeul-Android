package com.msg.network.datasource.post

import com.msg.network.response.post.*
// import com.msg.network.request.post.WritePostRequest
import com.msg.model.enumdata.FeedType
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface PostDataSource {
    fun getPostList(type: FeedType, size: Int, page: Int): Flow<GetPostListResponse>
    fun getDetailPost(id: UUID): Flow<GetDetailPostResponse>
}