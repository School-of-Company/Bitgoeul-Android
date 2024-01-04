package com.msg.data.repository.post

import com.msg.network.datasource.post.PostDataSource
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSource: PostDataSource
) : PostRepository {
}