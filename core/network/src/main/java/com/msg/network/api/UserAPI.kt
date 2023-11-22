package com.msg.network.api

import com.msg.model.remote.request.user.ChangePasswordRequest
import retrofit2.http.Body
import retrofit2.http.PATCH

interface UserAPI {
    @PATCH
    suspend fun changePassword(
        @Body body: ChangePasswordRequest
    )
}