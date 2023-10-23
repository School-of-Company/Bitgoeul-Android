package com.msg.network.api

import com.msg.model.remote.AuthTokenModel
import com.msg.model.remote.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): AuthTokenModel
}