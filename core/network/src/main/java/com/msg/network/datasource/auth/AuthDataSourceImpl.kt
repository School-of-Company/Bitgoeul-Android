package com.msg.network.datasource.auth

import com.msg.model.remote.AuthTokenModel
import com.msg.model.remote.request.LoginRequest
import com.msg.network.api.AuthAPI
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI
) : AuthDataSource {
    override suspend fun login(loginRequest: LoginRequest): Flow<AuthTokenModel> = flow {
        emit(
            BitgoeulApiHandler<AuthTokenModel>()
                .httpRequest { authAPI.login(loginRequest = loginRequest) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}