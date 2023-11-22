package com.msg.data.repository.user

import com.msg.model.remote.request.user.ChangePasswordRequest
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun changePassword(body: ChangePasswordRequest): Flow<Unit>
}