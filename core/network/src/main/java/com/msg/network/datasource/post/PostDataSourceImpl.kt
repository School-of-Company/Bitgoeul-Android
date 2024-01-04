package com.msg.network.datasource.post

import com.msg.model.remote.request.post.WritePostRequest
import com.msg.network.api.PostAPI
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostDataSourceImpl @Inject constructor(
    private val postAPI: PostAPI
) : PostDataSource {
    override suspend fun sendPost(body: WritePostRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { postAPI.sendPost(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}