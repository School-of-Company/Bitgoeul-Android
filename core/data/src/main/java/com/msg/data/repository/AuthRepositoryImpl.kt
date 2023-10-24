package com.msg.data.repository

import com.msg.datastore.AuthTokenDataSource
import com.msg.model.remote.AuthTokenModel
import com.msg.model.remote.request.LoginRequest
import com.msg.network.datasource.auth.AuthDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localDataSource: AuthTokenDataSource
) : AuthRepository {
    override suspend fun login(body: LoginRequest): Flow<AuthTokenModel> {
        return authDataSource.login(
            loginRequest = body
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
}