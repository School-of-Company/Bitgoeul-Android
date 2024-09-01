package com.msg.network.api

import com.msg.model.enumdata.FeedType
import com.msg.network.response.post.GetDetailPostResponse
import com.msg.network.response.post.GetPostListResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface PostAPI {
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

    @DELETE("post/{id}")
    suspend fun deletePost(
        @Path("id") id: UUID
    )
}