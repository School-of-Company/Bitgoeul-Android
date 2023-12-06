package com.msg.network.api

import com.msg.model.remote.response.admin.UseListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface AdminAPI {
    @GET("admin")
    suspend fun getUserList(
        @Body 
        @Query("keyword") keyword: String,
    ): UseListResponse
}