package com.msg.data.repository

import com.msg.datastore.AuthTokenDataSource
import com.msg.model.remote.AuthTokenModel
import com.msg.model.remote.request.LoginRequest
import com.msg.model.remote.request.SignUpCompanyInstructorRequest
import com.msg.model.remote.request.SignUpGovernmentRequest
import com.msg.model.remote.request.SignUpJobClubTeacherRequest
import com.msg.model.remote.request.SignUpProfessorRequest
import com.msg.model.remote.request.SignUpStudentRequest
import com.msg.network.datasource.auth.AuthDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localDataSource: AuthTokenDataSource
) : AuthRepository {
    override suspend fun login(body: LoginRequest): Flow<AuthTokenModel> {
        return authDataSource.login(
            body = body
        )
    }

    override suspend fun saveToken(data: AuthTokenModel) {
        data.let {
            localDataSource.setAccessToken(it.accessToken)
            localDataSource.setAccessTokenExp(it.accessExpiredAt)
            localDataSource.setRefreshToken(it.refreshToken)
            localDataSource.setRefreshTokenExp(it.refreshExpiredAt)
        }
    }

    override suspend fun signUpStudent(body: SignUpStudentRequest): Flow<Unit> {
        return authDataSource.signUpStudent(
            body = body
        )
    }

    override suspend fun signUpJobClubTeacher(body: SignUpJobClubTeacherRequest): Flow<Unit> {
        return authDataSource.signUpJobClubTeacher(
            body = body
        )
    }

    override suspend fun signUpProfessor(body: SignUpProfessorRequest): Flow<Unit> {
        return authDataSource.signUpProfessor(
            body = body
        )
    }

    override suspend fun signUpGovernment(body: SignUpGovernmentRequest): Flow<Unit> {
        return authDataSource.signUpGovernment(
            body = body
        )
    }

    override suspend fun signUpCompanyInstructor(body: SignUpCompanyInstructorRequest): Flow<Unit> {
        return authDataSource.signUpCompanyInstructor(
            body = body
        )
    }

    override suspend fun logout(): Flow<Unit> {
        return authDataSource.logout()
    }

    override suspend fun withdraw(): Flow<Unit> {
        return authDataSource.withdraw()
    }
}