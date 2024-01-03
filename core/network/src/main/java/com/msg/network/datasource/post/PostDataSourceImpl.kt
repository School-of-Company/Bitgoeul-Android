package com.msg.network.datasource.post

import com.msg.network.api.PostAPI
import javax.inject.Inject

class PostDataSourceImpl @Inject constructor(
    private val postAPI: PostAPI
) : PostDataSource {
}