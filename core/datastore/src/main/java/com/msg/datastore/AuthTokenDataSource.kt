package com.msg.datastore

import androidx.datastore.core.DataStore
import com.msg.model.remote.enumdatatype.Authority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthTokenDataSource @Inject constructor(
    private val authToken: DataStore<AuthToken>
) {
    suspend fun getAccessToken(): Flow<String> = authToken.data.map {
        it.accessToken ?: ""
    }

    suspend fun setAccessToken(accessToken: String) {
        authToken.updateData {
            it.toBuilder()
                .setAccessToken(accessToken)
                .build()
        }
    }

    suspend fun getAccessTokenExp(): Flow<String> = authToken.data.map {
        it.accessExp ?: ""
    }

    suspend fun setAccessTokenExp(accessTokenExp: String) {
        authToken.updateData {
            it.toBuilder()
                .setAccessExp(accessTokenExp)
                .build()
        }
    }

    suspend fun getRefreshToken(): Flow<String> = authToken.data.map {
        it.refreshToken ?: ""
    }

    suspend fun setRefreshToken(refreshToken: String) {
        authToken.updateData {
            it.toBuilder()
                .setRefreshToken(refreshToken)
                .build()
        }
    }

    suspend fun getRefreshTokenExp(): Flow<String> = authToken.data.map {
        it.refreshExp ?: ""
    }

    suspend fun setRefreshTokenExp(refreshTokenExp: String) {
        authToken.updateData {
            it.toBuilder()
                .setRefreshToken(refreshTokenExp)
                .build()
        }
    }

    suspend fun getAuthority(): Flow<String> = authToken.data.map {
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