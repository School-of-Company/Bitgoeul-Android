package com.msg.network.datasource.auth

import com.msg.model.remote.AuthTokenModel
import com.msg.model.remote.request.LoginRequest
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun login(loginRequest: LoginRequest): Flow<AuthTokenModel>
}