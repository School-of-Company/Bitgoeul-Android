package com.msg.network.datasource.post

import com.msg.model.remote.enumdatatype.FeedType
import com.msg.model.remote.request.post.WritePostRequest
import com.msg.model.remote.response.post.GetPostListResponse
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

    override suspend fun getPostList(type: FeedType, size: Int, page: Int): Flow<GetPostListResponse> = flow {
        emit(
            BitgoeulApiHandler<GetPostListResponse>()
                .httpRequest {
                    postAPI.getPostList(
                        type = type,
                        size = size,
                        page = page
                    )
                }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}