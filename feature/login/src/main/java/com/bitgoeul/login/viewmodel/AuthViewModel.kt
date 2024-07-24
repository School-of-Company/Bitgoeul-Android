package com.bitgoeul.login.viewmodel

import androidx.lifecycle.SavedStateHandle
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }
    private val _saveTokenResponse = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val saveTokenRequest = _saveTokenResponse.asStateFlow()

    private val _loginResponse = MutableStateFlow<Event<AuthTokenEntity>>(Event.Loading)
    val loginResponse = _loginResponse.asStateFlow()

    internal var email = savedStateHandle.getStateFlow(key = EMAIL, initialValue = "")

    internal var password = savedStateHandle.getStateFlow(key = PASSWORD, initialValue = "")

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

    internal fun onEmailChange(value: String) { savedStateHandle[EMAIL] = value }

    internal fun onPasswordChange(value: String) { savedStateHandle[PASSWORD] = value }
}
