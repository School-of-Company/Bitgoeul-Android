package com.bitgoeul.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitgoeul.login.util.Event
import com.bitgoeul.login.util.errorHandling
import com.msg.domain.auth.LoginUseCase
import com.msg.domain.auth.SaveTokenUseCase
import com.msg.model.remote.model.auth.AuthTokenModel
import com.msg.model.remote.request.auth.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
) : ViewModel() {
    private val _saveTokenResponse = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val saveTokenRequest = _saveTokenResponse.asStateFlow()

    private val _loginResponse = MutableStateFlow<Event<AuthTokenModel>>(Event.Loading)
    val loginResponse = _loginResponse.asStateFlow()

    internal fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        loginUseCase(
            body = LoginRequest(email, password)
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

    internal fun saveTokenData(data: AuthTokenModel) = viewModelScope.launch {
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
}
