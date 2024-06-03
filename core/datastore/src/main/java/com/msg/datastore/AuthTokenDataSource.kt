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

    fun getRefreshToken(): Flow<String> = authToken.data
        .transform { data ->
            emit(data.refreshToken ?: "")
        }
        emit(Unit)
    }

    fun getRefreshTokenExp(): Flow<LocalDateTime> = authToken.data
        .transform { data ->
            data.refreshExp?.let {
                emit(LocalDateTime.parse(it))
            }
        }
    }

    fun getAuthority(): Flow<Authority> = authToken.data
        .transform { data ->
            data.authority?.let { authority ->
                Authority.entries.firstOrNull { it.name == authority }?.let {
                    emit(it)
                }
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