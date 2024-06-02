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
    fun login(body: LoginRequest): Flow<AuthTokenModel>
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