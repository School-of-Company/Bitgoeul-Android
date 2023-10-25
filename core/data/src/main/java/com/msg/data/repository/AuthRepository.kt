package com.msg.data.repository

import com.msg.model.remote.AuthTokenModel
import com.msg.model.remote.request.LoginRequest
import com.msg.model.remote.request.SignUpJobClubTeacherRequest
import com.msg.model.remote.request.SignUpProfessorRequest
import com.msg.model.remote.request.SignUpStudentRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(body: LoginRequest): Flow<AuthTokenModel>
    suspend fun saveToken(data: AuthTokenModel)
    suspend fun signUpStudent(body: SignUpStudentRequest): Flow<Unit>
    suspend fun signUpJobClubTeacher(body: SignUpJobClubTeacherRequest): Flow<Unit>
    suspend fun signUpProfessor(body: SignUpProfessorRequest): Flow<Unit>
}