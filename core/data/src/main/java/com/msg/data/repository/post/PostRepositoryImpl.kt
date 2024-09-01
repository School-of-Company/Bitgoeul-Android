package com.msg.data.repository.post

import com.msg.data.mapper.post.toEntity
import com.msg.model.entity.post.GetDetailPostEntity
import com.msg.model.entity.post.GetPostListEntity
import com.msg.model.enumdata.FeedType
import com.msg.network.datasource.post.PostDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.util.UUID
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSource: PostDataSource,
) : PostRepository {
    override fun getPostList(
        type: FeedType,
        size: Int,
        page: Int,
    ): Flow<GetPostListEntity> {
        return postDataSource.getPostList(
            type = type,
            page = page,
            size = size
        ).transform { response ->
            response.toEntity()
        }
    }

    override fun getDetailPost(id: UUID): Flow<GetDetailPostEntity> {
        return postDataSource.getDetailPost(
            id = id
        ).transform { response ->
            response.toEntity()
        }
    }

    override fun deletePost(id: UUID): Flow<Unit> {
        return postDataSource.deletePost(id = id)
    }
}