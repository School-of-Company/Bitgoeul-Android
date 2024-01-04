package com.msg.network.datasource.post

import com.msg.model.remote.request.post.WritePostRequest
import kotlinx.coroutines.flow.Flow

interface PostDataSource {
    suspend fun sendPost(body: WritePostRequest): Flow<Unit>
}