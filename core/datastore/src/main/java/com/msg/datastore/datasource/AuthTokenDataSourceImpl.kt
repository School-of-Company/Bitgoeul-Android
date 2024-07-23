package com.msg.datastore.datasource

import androidx.datastore.core.DataStore
import com.msg.datastore.AuthToken
import com.msg.model.enumdata.Authority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import java.time.LocalDateTime
import javax.inject.Inject

class AuthTokenDataSourceImpl @Inject constructor(
    private val authToken: DataStore<AuthToken>
) : AuthTokenDataSource {

    override fun getAccessToken(): Flow<String> = authToken.data
        .transform { data ->
            emit(data.accessToken ?: "")
        }

    override fun setAccessToken(accessToken: String): Flow<Unit> {
        return updateAuthToken { it.toBuilder().setAccessToken(accessToken).build() }
    }

    override fun deleteAccessToken(): Flow<Unit> {
        return updateAuthToken { it.toBuilder().clearAccessToken().build() }
    }

    override fun getAccessTokenExp(): Flow<LocalDateTime> = authToken.data
        .transform { data ->
            data.accessExp?.let {
                emit(LocalDateTime.parse(it))
            }
        }

    override fun setAccessTokenExp(accessTokenExp: String): Flow<Unit> {
        return updateAuthToken { it.toBuilder().setAccessExp(accessTokenExp).build() }
    }

    override fun deleteAccessTokenExp(): Flow<Unit> {
        return updateAuthToken { it.toBuilder().clearAccessExp().build() }
    }

    override fun getRefreshToken(): Flow<String> = authToken.data
        .transform { data ->
            emit(data.refreshToken ?: "")
        }

    override fun setRefreshToken(refreshToken: String): Flow<Unit> {
        return updateAuthToken { it.toBuilder().setRefreshToken(refreshToken).build() }
    }

    override fun deleteRefreshToken(): Flow<Unit> {
        return updateAuthToken { it.toBuilder().clearRefreshToken().build() }
    }

    override fun getRefreshTokenExp(): Flow<LocalDateTime> = authToken.data
        .transform { data ->
            data.refreshExp?.let {
                emit(LocalDateTime.parse(it))
            }
        }

    override fun setRefreshTokenExp(refreshTokenExp: String): Flow<Unit> {
        return updateAuthToken { it.toBuilder().setRefreshExp(refreshTokenExp).build() }
    }

    override fun deleteRefreshTokenExp(): Flow<Unit> {
        return updateAuthToken { it.toBuilder().clearRefreshExp().build() }
    }

    override fun getAuthority(): Flow<Authority> = authToken.data
        .transform { data ->
            data.authority?.let { authority ->
                Authority.entries.firstOrNull { it.name == authority }?.let {
                    emit(it)
                }
            }
        }

    override fun setAuthority(authority: Authority): Flow<Unit> {
        return updateAuthToken { it.toBuilder().setAuthority(authority.name).build() }
    }

    override fun deleteAuthority(): Flow<Unit> {
        return updateAuthToken { it.toBuilder().clearAuthority().build() }
    }

    private fun updateAuthToken(update: (AuthToken) -> AuthToken): Flow<Unit> = flow {
        authToken.updateData { update(it) }
        emit(Unit)
    }
}