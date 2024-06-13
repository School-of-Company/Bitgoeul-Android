package com.msg.network.api

import com.msg.model.enumdata.FeedType
import com.msg.network.request.post.WritePostRequest
import com.msg.network.response.post.GetDetailPostResponse
import com.msg.network.response.post.GetPostListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
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

    @PATCH("post/{id}")
    suspend fun editPost(
        @Path("id") id: UUID,
        @Body body: WritePostRequest
    )

    @DELETE("post/{id}")
    suspend fun deletePost(
        @Path("id") id: UUID
    )
}