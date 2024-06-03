package com.msg.data.repository.auth

import Authority
import com.msg.datastore.datasource.AuthTokenDataSource
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthDataSource,
    private val localDataSource: AuthTokenDataSource
) : AuthRepository {
    override fun login(body: LoginRequest): Flow<AuthTokenModel> {
        return remoteDataSource.login(
            body = body
        )
    }

    override fun signUpStudent(body: SignUpStudentRequest): Flow<Unit> {
        return remoteDataSource.signUpStudent(
            body = body
        )
    }

    override fun signUpJobClubTeacher(body: SignUpJobClubTeacherRequest): Flow<Unit> {
        return remoteDataSource.signUpJobClubTeacher(
            body = body
        )
    }

    override fun signUpProfessor(body: SignUpProfessorRequest): Flow<Unit> {
        return remoteDataSource.signUpProfessor(
            body = body
        )
    }

    override fun signUpGovernment(body: SignUpGovernmentRequest): Flow<Unit> {
        return remoteDataSource.signUpGovernment(
            body = body
        )
    }

    override fun signUpCompanyInstructor(body: SignUpCompanyInstructorRequest): Flow<Unit> {
        return remoteDataSource.signUpCompanyInstructor(
            body = body
        )
    }

    override fun signUpBbozzakTeacher(body: SignUpBbozzakTeacherRequest): Flow<Unit> {
        return remoteDataSource.signUpBbozzakTeacher(
            body = body
        )
    }

    override fun findPassword(body: FindPasswordRequest): Flow<Unit> {
        return remoteDataSource.findPassword(
            body = body
        )
    }
    override fun logout(): Flow<Unit> {
        return remoteDataSource.logout()
    }

    override fun withdraw(): Flow<Unit> {
        return remoteDataSource.withdraw()
    }

    override fun saveToken(data: AuthTokenModel): Flow<Unit> = flow {
        localDataSource.setAccessToken(data.accessToken).first()
        localDataSource.setAccessTokenExp(data.accessExpiredAt).first()
        localDataSource.setRefreshToken(data.refreshToken).first()
        localDataSource.setRefreshTokenExp(data.refreshExpiredAt).first()
        localDataSource.setAuthority(data.authority).first()
    }

    override fun getAuthority(): Flow<Authority> {
        return localDataSource.getAuthority()
    }
}