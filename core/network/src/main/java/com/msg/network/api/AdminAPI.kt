package com.msg.network.api

import com.msg.network.request.admin.GetUserListRequest
import com.msg.network.response.admin.UserListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface AdminAPI {
    @GET("admin")
    suspend fun getUserList(
        @Body body: GetUserListRequest,
        @Query("keyword") keyword: String,
    ): UserListResponse
}