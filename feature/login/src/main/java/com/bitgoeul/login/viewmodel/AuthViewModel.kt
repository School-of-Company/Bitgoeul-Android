package com.bitgoeul.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitgoeul.login.viewmodel.util.Event
import com.bitgoeul.login.viewmodel.util.errorHandling
import com.msg.domain.auth.LoginUseCase
import com.msg.domain.auth.LogoutUseCase
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
    private val logoutUseCase: LogoutUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
) : ViewModel() {
    private val _saveTokenRequest = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val saveTokenRequest = _saveTokenRequest.asStateFlow()

    private val _loginResponse = MutableStateFlow<Event<AuthTokenModel>>(Event.Loading)
    val loginResponse = _loginResponse.asStateFlow()

    fun login(
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

    fun saveTokenData(data: AuthTokenModel) = viewModelScope.launch {
        saveTokenUseCase(
            data = data
        ).onSuccess {
            _saveTokenRequest.value = Event.Success()
        }.onFailure {
            _saveTokenRequest.value = it.errorHandling()
        }
    }
}
