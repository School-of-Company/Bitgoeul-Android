package com.msg.data.repository.user

import com.msg.model.remote.request.user.ChangePasswordRequest
import com.msg.model.remote.response.user.InquiryMyPageResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun changePassword(body: ChangePasswordRequest): Flow<Unit>
    fun inquiryMyPage(): Flow<InquiryMyPageResponse>
}