package com.msg.network.datasource.user

import com.msg.model.remote.request.user.ChangePasswordRequest
import com.msg.model.remote.response.user.InquiryMyPageResponse
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun changePassword(body: ChangePasswordRequest): Flow<Unit>
    suspend fun inquiryMyPage(): Flow<InquiryMyPageResponse>
}