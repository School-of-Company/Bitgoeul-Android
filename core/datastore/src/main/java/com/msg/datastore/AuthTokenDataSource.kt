package com.msg.datastore

import Authority
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import java.time.LocalDateTime
import javax.inject.Inject

class AuthTokenDataSource @Inject constructor(
    private val authToken: DataStore<AuthToken>,
) {
    fun getAccessToken(): Flow<String> = authToken.data
        .transform { data ->
            emit(data.accessToken ?: "")
        }

    fun setAccessToken(accessToken: String): Flow<Unit>  {
        return updateAuthToken { it.toBuilder().setAccessToken(accessToken).build() }
    }

    fun getAccessTokenExp(): Flow<LocalDateTime> = authToken.data
        .transform { data ->
            data.accessExp?.let {
                emit(LocalDateTime.parse(it))
            }
        }

    fun setAccessTokenExp(accessTokenExp: String): Flow<Unit> {
        return updateAuthToken { it.toBuilder().setAccessExp(accessTokenExp).build() }
    }

    fun getRefreshToken(): Flow<String> = authToken.data
        .transform { data ->
            emit(data.refreshToken ?: "")
        }

    fun setRefreshToken(refreshToken: String): Flow<Unit> {
        return updateAuthToken { it.toBuilder().setRefreshToken(refreshToken).build() }
    }

    fun getRefreshTokenExp(): Flow<LocalDateTime> = authToken.data
        .transform { data ->
            data.refreshExp?.let {
                emit(LocalDateTime.parse(it))
            }
        }

    fun setRefreshTokenExp(refreshTokenExp: String): Flow<Unit> {
        return updateAuthToken { it.toBuilder().setRefreshExp(refreshTokenExp).build() }
    }

    fun getAuthority(): Flow<Authority> = authToken.data
        .transform { data ->
            data.authority?.let { authority ->
                Authority.entries.firstOrNull { it.name == authority }?.let {
                    emit(it)
                }
            }
        }

    fun setAuthority(authority: Authority): Flow<Unit> {
        return updateAuthToken { it.toBuilder().setAuthority(authority.name).build() }
    }

    private fun updateAuthToken(update: (AuthToken) -> AuthToken): Flow<Unit> = flow {
        authToken.updateData { update(it) }
        emit(Unit)
    }
}