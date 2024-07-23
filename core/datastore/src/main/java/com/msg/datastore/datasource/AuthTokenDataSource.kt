package com.msg.datastore.datasource

import com.msg.model.enumdata.Authority
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime


interface AuthTokenDataSource {
    fun getAccessToken(): Flow<String>
    fun setAccessToken(accessToken: String): Flow<Unit>
    fun deleteAccessToken(): Flow<Unit>

    fun getAccessTokenExp(): Flow<LocalDateTime>
    fun setAccessTokenExp(accessTokenExp: String): Flow<Unit>
    fun deleteAccessTokenExp(): Flow<Unit>

    fun getRefreshToken(): Flow<String>
    fun setRefreshToken(refreshToken: String): Flow<Unit>
    fun deleteRefreshToken(): Flow<Unit>

    fun getRefreshTokenExp(): Flow<LocalDateTime>
    fun setRefreshTokenExp(refreshTokenExp: String): Flow<Unit>
    fun deleteRefreshTokenExp(): Flow<Unit>

    fun getAuthority(): Flow<Authority>
    fun setAuthority(authority: Authority): Flow<Unit>
    fun deleteAuthority(): Flow<Unit>
}