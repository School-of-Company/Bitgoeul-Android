package com.msg.bitgoeul_android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitgoeul.login.navigation.loginRoute
import com.msg.data.repository.auth.AuthRepository
import com.msg.datastore.datasource.AuthTokenDataSource
import com.msg.domain.usecase.auth.TokenAccessUseCase
import com.msg.main.navigation.mainPageRoute
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
        checkExp()
    }

    private fun checkExp() {
        if (refreshTokenTime.isDateExpired()) {
            with(authTokenDataSource) {
                deleteRefreshTokenExp()
                deleteAccessTokenExp()
                deleteAuthority()
            }
            refreshToken = ""
            refreshTokenTime = ""
        }

        if (refreshToken.isEmpty() || refreshToken == "") {
            _navigateRoute = loginRoute
        } else {
            _navigateRoute = mainPageRoute
            login()
        }
    }

    private fun login() = viewModelScope.launch {
        tokenAccessUseCase("Bearer $refreshToken")
            .onSuccess { result ->
                result.catch {
                    Log.e("Login Failure", it.message.toString())
                }.collect { newToken ->
                    with(authTokenDataSource) {
                        setAccessToken(newToken.accessToken)
                        setAccessTokenExp(newToken.accessExpiredAt)
                        setRefreshToken(newToken.refreshToken)
                        setRefreshTokenExp(newToken.refreshExpiredAt)
                    }
                    refreshToken = newToken.refreshToken
                    refreshTokenTime = newToken.refreshExpiredAt
                    _navigateRoute = mainPageRoute
                }
            }.onFailure {
                Log.e("Login onFailure", it.message.toString())
            }
    }
}