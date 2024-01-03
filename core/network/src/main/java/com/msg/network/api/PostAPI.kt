package com.msg.network.api

import com.msg.model.remote.enumdatatype.FeedType
import com.msg.model.remote.request.post.WritePostRequest
import com.msg.model.remote.response.post.GetDetailPostResponse
import com.msg.model.remote.response.post.GetPostListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

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
    ): GetPostListResponse

    @GET("post/{id}")
    suspend fun getDetailPost(
        @Path("id") id: UUID
    ): GetDetailPostResponse
}