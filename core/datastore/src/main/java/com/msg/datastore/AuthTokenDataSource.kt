package com.msg.datastore

import androidx.datastore.core.DataStore
import com.msg.model.remote.enumdatatype.Authority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

class AuthTokenDataSource @Inject constructor(
    private val authToken: DataStore<AuthToken>,
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

    fun getAccessTokenExp(): Flow<LocalDateTime> =
        authToken.data.mapNotNull { it.accessExp }.map { accessExp ->
            LocalDateTime.parse(accessExp)
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

    fun getRefreshTokenExp(): Flow<LocalDateTime> =
        authToken.data.mapNotNull { it.refreshExp }.map { refreshExp ->
            LocalDateTime.parse(refreshExp)
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