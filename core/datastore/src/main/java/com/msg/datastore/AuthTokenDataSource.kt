package com.msg.datastore

import androidx.datastore.core.DataStore
import Authority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import java.time.LocalDateTime
import javax.inject.Inject

class AuthTokenDataSource @Inject constructor(
    private val authToken: DataStore<AuthToken>,
) {
    fun getAccessToken(): Flow<String> = authToken.data.map {
        it.accessToken ?: ""
    }

    fun setAccessToken(accessToken: String): Flow<Unit> = flow {
        authToken.updateData {
            it.toBuilder()
                .setAccessToken(accessToken)
                .build()
        }
        emit(Unit)
    }

    fun getAccessTokenExp(): Flow<LocalDateTime> =
        authToken.data.mapNotNull { it.accessExp }.map { accessExp ->
            LocalDateTime.parse(accessExp)
        }


    fun setAccessTokenExp(accessTokenExp: String): Flow<Unit> = flow {
        authToken.updateData {
            it.toBuilder()
                .setAccessExp(accessTokenExp)
                .build()
        }
        emit(Unit)
    }

    fun getRefreshToken(): Flow<String> = authToken.data.map {
        it.refreshToken ?: ""
    }

    fun setRefreshToken(refreshToken: String): Flow<Unit> = flow {
        authToken.updateData {
            it.toBuilder()
                .setRefreshToken(refreshToken)
                .build()
        }
        emit(Unit)
    }

    fun getRefreshTokenExp(): Flow<LocalDateTime> =
        authToken.data.mapNotNull { it.refreshExp?.let { refreshExp ->
            LocalDateTime.parse(refreshExp)
        } }


    fun setRefreshTokenExp(refreshTokenExp: String): Flow<Unit> = flow {
        authToken.updateData {
            it.toBuilder()
                .setRefreshExp(refreshTokenExp)
                .build()
        }
    }

    fun getAuthority(): Flow<Authority> = authToken.data.mapNotNull { data ->
        data.authority?.let { authority ->
            when (authority) {
                "ROLE_USER" -> Authority.ROLE_USER
                "ROLE_ADMIN" -> Authority.ROLE_ADMIN
                "ROLE_STUDENT" -> Authority.ROLE_STUDENT
                "ROLE_TEACHER" -> Authority.ROLE_TEACHER
                "ROLE_BBOZZAK" -> Authority.ROLE_BBOZZAK
                "ROLE_PROFESSOR" -> Authority.ROLE_PROFESSOR
                "ROLE_COMPANY_INSTRUCTOR" -> Authority.ROLE_COMPANY_INSTRUCTOR
                "ROLE_GOVERNMENT" -> Authority.ROLE_GOVERNMENT
                else -> null
            }
        }
    }


    fun setAuthority(authority: Authority): Flow<Unit> = flow {
        authToken.updateData {
            it.toBuilder()
                .setAuthority(authority.toString())
                .build()
        }
        emit(Unit)
    }
}