package com.msg.network.datasource.auth

import com.msg.network.api.AuthAPI
import com.msg.network.request.auth.*
import com.msg.network.response.auth.*
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI
) : AuthDataSource {
    override fun login(body: LoginRequest): Flow<AuthTokenResponse> =
        makeRequest { authAPI.login(body = body) }

    override fun signUpStudent(body: SignUpStudentRequest): Flow<Unit> =
        makeRequest { authAPI.signUpStudent(body = body) }

    override fun signUpJobClubTeacher(body: SignUpJobClubTeacherRequest): Flow<Unit> =
        makeRequest { authAPI.signUpJobClubTeacher(body = body) }

    override fun signUpProfessor(body: SignUpProfessorRequest): Flow<Unit> =
        makeRequest { authAPI.signUpProfessor(body = body) }

    override fun signUpGovernment(body: SignUpGovernmentRequest): Flow<Unit> =
        makeRequest { authAPI.signUpGovernment(body = body) }

    override fun signUpCompanyInstructor(body: SignUpCompanyInstructorRequest): Flow<Unit> =
        makeRequest { authAPI.signUpCompanyInstructor(body = body) }

    override fun signUpBbozzakTeacher(body: SignUpBbozzakTeacherRequest): Flow<Unit> =
        makeRequest { authAPI.signUpBbozzakTeacher(body = body) }

    override fun findPassword(body: FindPasswordRequest): Flow<Unit> =
        makeRequest { authAPI.findPassword(body = body) }

    override fun logout(): Flow<Unit> =
        makeRequest { authAPI.logout() }

    override fun withdraw(): Flow<Unit> =
        makeRequest { authAPI.withdraw() }

    override fun tokenAccess(): Flow<TokenAccessResponse> =
        makeRequest { authAPI.tokenAccess() }
}