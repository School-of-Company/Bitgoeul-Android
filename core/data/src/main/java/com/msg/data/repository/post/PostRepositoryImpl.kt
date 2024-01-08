package com.msg.data.repository.post

import com.msg.model.remote.request.post.WritePostRequest
import com.msg.network.datasource.post.PostDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSource: PostDataSource
) : PostRepository {
    override suspend fun sendPost(body: WritePostRequest): Flow<Unit> {
        return postDataSource.sendPost(body = body)
    }
}