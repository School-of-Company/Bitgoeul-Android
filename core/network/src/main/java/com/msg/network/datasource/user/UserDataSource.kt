package com.msg.network.datasource.user

import com.msg.network.request.user.ChangePasswordRequest
import com.msg.network.response.user.GetMyPageResponse
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun changePassword(body: ChangePasswordRequest): Flow<Unit>
    fun getMyPage(): Flow<GetMyPageResponse>
}