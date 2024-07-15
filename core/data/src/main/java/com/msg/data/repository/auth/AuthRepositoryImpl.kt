package com.msg.data.repository.auth

import com.msg.data.mapper.auth.toEntity
import com.msg.data.mapper.auth.toRequest
import com.msg.datastore.datasource.AuthTokenDataSource
import com.msg.model.entity.auth.AuthTokenEntity
import com.msg.model.enumdata.Authority
import com.msg.model.param.auth.FindPasswordParam
import com.msg.model.param.auth.LoginParam
import com.msg.model.param.auth.SignUpBbozzakTeacherParam
import com.msg.model.param.auth.SignUpCompanyInstructorParam
import com.msg.model.param.auth.SignUpGovernmentParam
import com.msg.model.param.auth.SignUpJobClubTeacherParam
import com.msg.model.param.auth.SignUpProfessorParam
import com.msg.model.param.auth.SignUpStudentParam
import com.msg.network.datasource.auth.AuthDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthDataSource,
    private val localDataSource: AuthTokenDataSource
) : AuthRepository {
    override fun login(body: LoginParam): Flow<AuthTokenEntity> {
        return remoteDataSource.login(
            body = body.toRequest()
        ).transform { response ->
            emit(response.toEntity())
        }
    }

    override fun signUpStudent(body: SignUpStudentParam): Flow<Unit> {
        return remoteDataSource.signUpStudent(
            body = body.toRequest()
        )
    }

    override fun signUpJobClubTeacher(body: SignUpJobClubTeacherParam): Flow<Unit> {
        return remoteDataSource.signUpJobClubTeacher(
            body = body.toRequest()
        )
    }

    override fun signUpProfessor(body: SignUpProfessorParam): Flow<Unit> {
        return remoteDataSource.signUpProfessor(
            body = body.toRequest()
        )
    }

    override fun signUpGovernment(body: SignUpGovernmentParam): Flow<Unit> {
        return remoteDataSource.signUpGovernment(
            body = body.toRequest()
        )
    }

    override fun signUpCompanyInstructor(body: SignUpCompanyInstructorParam): Flow<Unit> {
        return remoteDataSource.signUpCompanyInstructor(
            body = body.toRequest()
        )
    }

    override fun signUpBbozzakTeacher(body: SignUpBbozzakTeacherParam): Flow<Unit> {
        return remoteDataSource.signUpBbozzakTeacher(
            body = body.toRequest()
        )
    }

    override fun findPassword(body: FindPasswordParam): Flow<Unit> {
        return remoteDataSource.findPassword(
            body = body.toRequest()
        )
    }
    override fun logout(): Flow<Unit> {
        return remoteDataSource.logout()
    }

    override fun withdraw(): Flow<Unit> {
        return remoteDataSource.withdraw()
    }

    override fun saveToken(data: AuthTokenEntity): Flow<Unit> = flow {
        with(localDataSource) {
            setAccessToken(data.accessToken).first()
            setAccessTokenExp(data.accessExpiredAt).first()
            setRefreshToken(data.refreshToken).first()
            setRefreshTokenExp(data.refreshExpiredAt).first()
            setAuthority(data.authority).first()
        }
    }

    override fun getAuthority(): Flow<Authority> {
        return localDataSource.getAuthority()
    }
}