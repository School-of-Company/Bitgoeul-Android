package com.msg.data.repository.user

import com.msg.model.entity.user.InquiryMyPageEntity
import com.msg.model.param.user.ChangePasswordParam
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun changePassword(body: ChangePasswordParam): Flow<Unit>
    fun inquiryMyPage(): Flow<InquiryMyPageEntity>
}