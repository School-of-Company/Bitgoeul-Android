package com.msg.datastore

import androidx.datastore.core.DataStore
import com.msg.model.remote.enumdatatype.Authority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class AuthTokenDataSource @Inject constructor(
    private val authToken: DataStore<AuthToken>
) {
    fun getAccessToken(): Flow<String> = authToken.data.map {
        it.accessToken ?: ""
    }

    suspend fun setAccessToken(accessToken: String) {
        authToken.updateData {
            it.toBuilder()
                .setAccessToken(accessToken)
                .build()
        }
    }

    fun getAccessTokenExp(): Flow<LocalDateTime> = authToken.data.map {
        it.accessExp ?: ""
    }.map { accessTokenExpiredAt ->
        accessTokenExpiredAt.let {
            LocalDateTime.now()
        }
    }

    suspend fun setAccessTokenExp(accessTokenExp: LocalDateTime) {
        authToken.updateData {
            it.toBuilder()
                .setAccessExp(accessTokenExp.toString())
                .build()
        }
    }

    fun getRefreshToken(): Flow<String> = authToken.data.map {
        it.refreshToken ?: ""
    }

    suspend fun setRefreshToken(refreshToken: String) {
        authToken.updateData {
            it.toBuilder()
                .setRefreshToken(refreshToken)
                .build()
        }
    }

    fun getRefreshTokenExp(): Flow<LocalDateTime> = authToken.data.map {
        it.refreshExp ?: ""
    }.map { refreshTokenExpiredAt ->
        refreshTokenExpiredAt.let {
            LocalDateTime.now()
        }
    }

    suspend fun setRefreshTokenExp(refreshTokenExp: LocalDateTime) {
        authToken.updateData {
            it.toBuilder()
                .setRefreshExp(refreshTokenExp.toString())
                .build()
        }
    }

    fun getAuthority(): Flow<String> = authToken.data.map {
        it.authority ?: ""
    }

    suspend fun setAuthority(authority: Authority) {
        authToken.updateData {
            it.toBuilder()
                .setAuthority(authority.toString())
                .build()
        }
    }
}