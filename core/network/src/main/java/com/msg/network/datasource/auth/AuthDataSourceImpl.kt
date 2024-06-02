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
    override fun login(body: LoginRequest): Flow<AuthTokenModel> = flow {
        emit(
            BitgoeulApiHandler<AuthTokenModel>()
                .httpRequest { authAPI.login(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun signUpStudent(body: SignUpStudentRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { authAPI.signUpStudent(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun signUpJobClubTeacher(body: SignUpJobClubTeacherRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { authAPI.signUpJobClubTeacher(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun signUpProfessor(body: SignUpProfessorRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { authAPI.signUpProfessor(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)


    override fun signUpGovernment(body: SignUpGovernmentRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { authAPI.signUpGovernment(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun signUpCompanyInstructor(body: SignUpCompanyInstructorRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { authAPI.signUpCompanyInstructor(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun signUpBbozzakTeacher(body: SignUpBbozzakTeacherRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { authAPI.signUpBbozzakTeacher(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun findPassword(body: FindPasswordRequest): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { authAPI.findPassword(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun logout(): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { authAPI.logout() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override fun withdraw(): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { authAPI.withdraw() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}