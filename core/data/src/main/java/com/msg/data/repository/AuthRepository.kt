package com.msg.data.repository

import com.msg.model.remote.AuthTokenModel
import com.msg.model.remote.request.LoginRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(body: LoginRequest): Flow<AuthTokenModel>
    suspend fun saveToken(data: AuthTokenModel)
}