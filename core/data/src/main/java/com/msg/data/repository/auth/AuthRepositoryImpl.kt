package com.msg.data.repository.auth

import com.msg.datastore.AuthTokenDataSource
import com.msg.model.remote.model.auth.AuthTokenModel
import com.msg.model.remote.request.auth.FindPasswordRequest
import com.msg.model.remote.request.auth.LoginRequest
import com.msg.model.remote.request.auth.SignUpBbozzakTeacherRequest
import com.msg.model.remote.request.auth.SignUpCompanyInstructorRequest
import com.msg.model.remote.request.auth.SignUpGovernmentRequest
import com.msg.model.remote.request.auth.SignUpJobClubTeacherRequest
import com.msg.model.remote.request.auth.SignUpProfessorRequest
import com.msg.model.remote.request.auth.SignUpStudentRequest
import com.msg.network.datasource.auth.AuthDataSource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localDataSource: AuthTokenDataSource
) : AuthRepository {
    override fun login(body: LoginRequest): Flow<AuthTokenModel> {
        return authDataSource.login(
            body = body
        )
    }

    override suspend fun saveToken(data: AuthTokenModel) {
        data.let { loginResponse ->
            localDataSource.setAccessToken(loginResponse.accessToken)
            localDataSource.setAccessTokenExp(loginResponse.accessExpiredAt)
            localDataSource.setRefreshToken(loginResponse.refreshToken)
            localDataSource.setRefreshTokenExp(loginResponse.refreshExpiredAt)
            localDataSource.setAuthority(loginResponse.authority)
        }
    }

    override fun signUpStudent(body: SignUpStudentRequest): Flow<Unit> {
        return authDataSource.signUpStudent(
            body = body
        )
    }

    override fun signUpJobClubTeacher(body: SignUpJobClubTeacherRequest): Flow<Unit> {
        return authDataSource.signUpJobClubTeacher(
            body = body
        )
    }

    override fun signUpProfessor(body: SignUpProfessorRequest): Flow<Unit> {
        return authDataSource.signUpProfessor(
            body = body
        )
    }

    override fun signUpGovernment(body: SignUpGovernmentRequest): Flow<Unit> {
        return authDataSource.signUpGovernment(
            body = body
        )
    }

    override fun signUpCompanyInstructor(body: SignUpCompanyInstructorRequest): Flow<Unit> {
        return authDataSource.signUpCompanyInstructor(
            body = body
        )
    }

    override fun signUpBbozzakTeacher(body: SignUpBbozzakTeacherRequest): Flow<Unit> {
        return authDataSource.signUpBbozzakTeacher(
            body = body
        )
    }

    override fun findPassword(body: FindPasswordRequest): Flow<Unit> {
        return authDataSource.findPassword(
            body = body
        )
    }
    override fun logout(): Flow<Unit> {
        return authDataSource.logout()
    }

    override fun withdraw(): Flow<Unit> {
        return authDataSource.withdraw()
    }
}