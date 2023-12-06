package com.msg.network.api

import retrofit2.http.GET

interface AdminAPI {
    @GET("admin")
    suspend fun getUserList(): List<>
}