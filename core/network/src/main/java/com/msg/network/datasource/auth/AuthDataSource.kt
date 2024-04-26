package com.msg.network.datasource.auth

import com.msg.model.remote.model.auth.AuthTokenModel
import com.msg.model.remote.request.auth.FindPasswordRequest
import com.msg.model.remote.request.auth.LoginRequest
import com.msg.model.remote.request.auth.SignUpBbozzakTeacherRequest
import com.msg.model.remote.request.auth.SignUpCompanyInstructorRequest
import com.msg.model.remote.request.auth.SignUpGovernmentRequest
import com.msg.model.remote.request.auth.SignUpJobClubTeacherRequest
import com.msg.model.remote.request.auth.SignUpProfessorRequest
import com.msg.model.remote.request.auth.SignUpStudentRequest
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun login(body: LoginRequest): Flow<AuthTokenModel>
    suspend fun signUpStudent(body: SignUpStudentRequest): Flow<Unit>
    suspend fun signUpJobClubTeacher(body: SignUpJobClubTeacherRequest): Flow<Unit>
    suspend fun signUpProfessor(body: SignUpProfessorRequest): Flow<Unit>
    suspend fun signUpGovernment(body: SignUpGovernmentRequest): Flow<Unit>
    suspend fun signUpCompanyInstructor(body: SignUpCompanyInstructorRequest): Flow<Unit>
    suspend fun signUpBbozzakTeacher(body: SignUpBbozzakTeacherRequest): Flow<Unit>
    suspend fun findPassword(body: FindPasswordRequest): Flow<Unit>
    suspend fun logout(): Flow<Unit>
    suspend fun withdraw(): Flow<Unit>
}