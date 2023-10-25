package com.msg.network.datasource.auth

import com.msg.model.remote.AuthTokenModel
import com.msg.model.remote.request.LoginRequest
import com.msg.model.remote.request.SignUpStudentRequest
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun login(body: LoginRequest): Flow<AuthTokenModel>
    suspend fun signUpStudent(body: SignUpStudentRequest): Flow<Unit>
}