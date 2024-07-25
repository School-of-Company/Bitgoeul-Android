package com.bitgoeul.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitgoeul.login.navigation.loginRoute
import com.msg.common.errorhandling.errorHandling
import com.msg.common.event.Event
import com.msg.datastore.datasource.AuthTokenDataSource
import com.msg.domain.usecase.auth.LoginUseCase
import com.msg.domain.usecase.auth.SaveTokenUseCase
import com.msg.domain.usecase.auth.TokenAccessUseCase
import com.msg.main.navigation.mainPageRoute
import com.msg.model.entity.auth.AuthTokenEntity
import com.msg.model.entity.auth.TokenAccessEntity
import com.msg.model.param.auth.LoginParam
import com.msg.network.util.isDateExpired
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val tokenAccessUseCase: TokenAccessUseCase,
    private val authTokenDataSource: AuthTokenDataSource,
) : ViewModel() {
    private var refreshToken: String = authTokenDataSource.getRefreshToken().toString()
    private var refreshTokenTime: String = authTokenDataSource.getRefreshTokenExp().toString()

    private val _saveTokenResponse = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val saveTokenRequest = _saveTokenResponse.asStateFlow()

    private val _loginResponse = MutableStateFlow<Event<AuthTokenEntity>>(Event.Loading)
    val loginResponse = _loginResponse.asStateFlow()

    private val _navigateRoute = MutableStateFlow(loginRoute)
    val navigateRoute: StateFlow<String> get() = _navigateRoute

    fun validateTokenNavigate() = viewModelScope.launch {
        Log.d("AuthViewModel", "Validating token")
        Log.d("AuthViewModel", "RefreshTime : $refreshTokenTime")
        Log.d("AuthViewModel", "RefreshToken : $refreshToken")
        if (tokenValid()) {
            Log.d("AuthViewModel", "Token is valid, refreshing token")
            _navigateRoute.value = mainPageRoute
        } else {
            Log.d("AuthViewModel", "Token is invalid, clearing token data")
            refreshToken()
        }
    }

    internal fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        loginUseCase(
            body = LoginParam(email, password)
        ).onSuccess {
            it.catch { remoteError ->
                _loginResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _loginResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _loginResponse.value = it.errorHandling()
        }
    }

    internal fun saveTokenData(
        data: AuthTokenEntity
    ) = viewModelScope.launch {
        saveTokenUseCase(
            data = data
        ).onSuccess {
            it.catch { remoteError ->
                _saveTokenResponse.value = remoteError.errorHandling()
            }.collect {
                _saveTokenResponse.value = Event.Success()
            }
        }.onFailure {
            _saveTokenResponse.value = it.errorHandling()
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
        return refreshToken.isNotEmpty() && refreshTokenTime.isNotEmpty() && !refreshTokenTime.isDateExpired()
    }

    private fun refreshToken() = viewModelScope.launch {
        Log.d("AuthViewModel", "Attempting to refresh token")
        tokenAccessUseCase("Bearer $refreshToken")
            .onSuccess { result ->
                result.catch {
                    Log.e("Login Failure", it.message.toString())
                }.collect { newToken ->
                    Log.d("AuthViewModel", "Token refreshed successfully")
                    updateTokenData(newToken)
                    _navigateRoute.value = mainPageRoute
                }
            }.onFailure {
                Log.e("Login onFailure", it.message.toString())
                clearTokenData()
                _navigateRoute.value = loginRoute
            }
    }

    private fun updateTokenData(newToken: TokenAccessEntity) {
        viewModelScope.launch {
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
}
