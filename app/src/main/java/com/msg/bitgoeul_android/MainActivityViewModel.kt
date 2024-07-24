package com.msg.bitgoeul_android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitgoeul.login.navigation.loginRoute
import com.msg.data.repository.auth.AuthRepository
import com.msg.datastore.datasource.AuthTokenDataSource
import com.msg.domain.usecase.auth.TokenAccessUseCase
import com.msg.main.navigation.mainPageRoute
import com.msg.model.entity.auth.TokenAccessEntity
import com.msg.network.util.isDateExpired
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val tokenAccessUseCase: TokenAccessUseCase,
    private val authTokenDataSource: AuthTokenDataSource,
) : ViewModel() {
    private var refreshToken: String = authTokenDataSource.getRefreshToken().toString()
    private var refreshTokenTime: String = authTokenDataSource.getRefreshTokenExp().toString()

    private var _navigateRoute: String = loginRoute
    val navigateRoute: String
        get() = _navigateRoute

    init {
        validateToken()
    }

    private fun validateToken() {
        if (tokenValid()) {
            _navigateRoute = mainPageRoute
            refreshToken()
        } else {
            clearTokenData()
            _navigateRoute = loginRoute
        }
    }

    private fun clearTokenData() {
        with(authTokenDataSource) {
            deleteRefreshTokenExp()
            deleteAccessTokenExp()
            deleteAuthority()
        }
        refreshToken = ""
        refreshTokenTime = ""
    }

    private fun tokenValid() : Boolean {
        return refreshToken.isNotEmpty() && !refreshTokenTime.isDateExpired()
    }


    private fun refreshToken() = viewModelScope.launch {
        tokenAccessUseCase("Bearer $refreshToken")
            .onSuccess { result ->
                result.catch {
                    Log.e("Login Failure", it.message.toString())
                }.collect { newToken ->
                    updateTokenData(newToken)
                    _navigateRoute = mainPageRoute
                }
            }.onFailure {
                Log.e("Login onFailure", it.message.toString())
            }
    }

    private fun updateTokenData(newToken: TokenAccessEntity) {
        with(authTokenDataSource) {
            setAccessToken(newToken.accessToken)
            setAccessTokenExp(newToken.accessExpiredAt)
            setRefreshToken(newToken.refreshToken)
            setRefreshTokenExp(newToken.refreshExpiredAt)
        }
        refreshToken = newToken.refreshToken
        refreshTokenTime = newToken.refreshExpiredAt
    }
}