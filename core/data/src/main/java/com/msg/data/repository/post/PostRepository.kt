package com.msg.data.repository.post

import com.msg.model.remote.request.post.WritePostRequest
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun sendPost(body: WritePostRequest): Flow<Unit>
}