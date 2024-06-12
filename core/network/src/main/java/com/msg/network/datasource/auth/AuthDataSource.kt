package com.msg.network.datasource.auth

import com.msg.network.request.auth.FindPasswordRequest
import com.msg.network.request.auth.LoginRequest
import com.msg.network.request.auth.SignUpBbozzakTeacherRequest
import com.msg.network.request.auth.SignUpCompanyInstructorRequest
import com.msg.network.request.auth.SignUpGovernmentRequest
import com.msg.network.request.auth.SignUpJobClubTeacherRequest
import com.msg.network.request.auth.SignUpProfessorRequest
import com.msg.network.request.auth.SignUpStudentRequest
import com.msg.network.response.auth.AuthTokenResponse
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun login(body: LoginRequest): Flow<AuthTokenResponse>
    fun signUpStudent(body: SignUpStudentRequest): Flow<Unit>
    fun signUpJobClubTeacher(body: SignUpJobClubTeacherRequest): Flow<Unit>
    fun signUpProfessor(body: SignUpProfessorRequest): Flow<Unit>
    fun signUpGovernment(body: SignUpGovernmentRequest): Flow<Unit>
    fun signUpCompanyInstructor(body: SignUpCompanyInstructorRequest): Flow<Unit>
    fun signUpBbozzakTeacher(body: SignUpBbozzakTeacherRequest): Flow<Unit>
    fun findPassword(body: FindPasswordRequest): Flow<Unit>
    fun logout(): Flow<Unit>
    fun withdraw(): Flow<Unit>
}