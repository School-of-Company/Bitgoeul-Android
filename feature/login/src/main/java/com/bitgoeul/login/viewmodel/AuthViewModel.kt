package com.bitgoeul.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitgoeul.login.viewmodel.util.Event
import com.bitgoeul.login.viewmodel.util.errorHandling
import com.msg.domain.auth.LoginUseCase
import com.msg.domain.auth.LogoutUseCase
import com.msg.domain.auth.SaveTokenUseCase
import com.msg.domain.auth.WithdrawUseCase
import com.msg.model.remote.AuthTokenModel
import com.msg.model.remote.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val withdrawUseCase: WithdrawUseCase,
) : ViewModel(){
    private val _saveTokenRequest = MutableLiveData<Event<Nothing>>()
    val saveTokenRequest: LiveData<Event<Nothing>> get() = _saveTokenRequest

    private val _loginRequest = MutableLiveData<Event<AuthTokenModel>>()
    val loginRequest: LiveData<Event<AuthTokenModel>> get() = _loginRequest

    fun login(body: LoginRequest) = viewModelScope.launch {
        loginUseCase(
            body = body
        ).onSuccess {
            it.catch {remoteError ->
                _loginRequest.value = remoteError.errorHandling()
            }.collect { response ->
                _loginRequest.value = Event.Success(data = response)
            }
        }.onFailure {
            _loginRequest.value = it.errorHandling()
        }
    }
}
