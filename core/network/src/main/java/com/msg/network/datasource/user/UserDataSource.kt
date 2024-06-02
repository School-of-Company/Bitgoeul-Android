package com.msg.network.datasource.user

import com.msg.model.remote.request.user.ChangePasswordRequest
import com.msg.model.remote.response.user.InquiryMyPageResponse
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun changePassword(body: ChangePasswordRequest): Flow<Unit>
    fun inquiryMyPage(): Flow<InquiryMyPageResponse>
}