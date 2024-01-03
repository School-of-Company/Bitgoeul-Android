package com.msg.network.api

import com.msg.model.remote.enumdatatype.FeedType
import com.msg.model.remote.request.post.WritePostRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PostAPI {

    @POST("post")
    suspend fun sendPost(
        @Body body: WritePostRequest
    )

    @GET("post")
    suspend fun getPostList(
        @Query("type") type: FeedType,
        @Query("size") size: Int,
        @Query("page") page: Int
    )
}