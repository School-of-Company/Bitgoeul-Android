package com.msg.network.datasource.post

import com.msg.model.enumdata.FeedType
import com.msg.network.api.PostAPI
import com.msg.network.request.post.WritePostRequest
import com.msg.network.response.post.GetDetailPostResponse
import com.msg.network.response.post.GetPostListResponse
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class PostDataSourceImpl @Inject constructor(
    private val postAPI: PostAPI
) : PostDataSource {
    override fun sendPost(body: WritePostRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { postAPI.sendPost(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun getPostList(type: FeedType, size: Int, page: Int): Flow<GetPostListResponse> = flow {
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

    override fun getDetailPost(id: UUID): Flow<GetDetailPostResponse> = flow {
        emit(
            BitgoeulApiHandler<GetDetailPostResponse>()
                .httpRequest { postAPI.getDetailPost(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun editPost(id: UUID, body: WritePostRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest {
                    postAPI.editPost(
                        id = id,
                        body = body
                    )
                }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun deletePost(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { postAPI.deletePost(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}