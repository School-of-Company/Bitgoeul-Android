package com.msg.network.datasource.auth

import com.msg.model.remote.AuthTokenModel
import com.msg.model.remote.request.LoginRequest
import com.msg.model.remote.request.SignUpStudentRequest
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
    override suspend fun login(body: LoginRequest): Flow<AuthTokenModel> = flow {
        emit(
            BitgoeulApiHandler<AuthTokenModel>()
                .httpRequest { authAPI.login(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun signUpStudent(body: SignUpStudentRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { authAPI.signUpStudent(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}