package com.msg.network.api

import com.msg.network.request.user.ChangePasswordRequest
import com.msg.network.response.user.GetMyPageResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserAPI {
    @PATCH("user")
    suspend fun changePassword(
        @Body body: ChangePasswordRequest
    )

    @GET("user")
    suspend fun getMyPage(): GetMyPageResponse
}