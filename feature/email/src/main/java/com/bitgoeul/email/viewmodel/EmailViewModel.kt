package com.bitgoeul.email.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.common.errorhandling.errorHandling
import com.msg.common.event.Event
import com.msg.domain.usecase.auth.FindPasswordUseCase
import com.msg.domain.usecase.email.GetEmailAuthenticateStatusUseCase
import com.msg.domain.usecase.email.SendLinkToEmailUseCase
import com.msg.model.entity.email.GetEmailAuthenticateStatusEntity
import com.msg.model.param.auth.FindPasswordParam
import com.msg.model.param.email.SendLinkToEmailParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailViewModel @Inject constructor(
    private val sendLinkToEmailUseCase: SendLinkToEmailUseCase,
    private val getEmailAuthenticateStatusUseCase: GetEmailAuthenticateStatusUseCase,
    private val findPasswordUseCase: FindPasswordUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val EMAIL = "email"

    }

    private val _sendLinkToEmailResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val sendLinkToEmailResponse = _sendLinkToEmailResponse.asStateFlow()

    private val _getEmailAuthenticateStatusResponse = MutableStateFlow<Event<GetEmailAuthenticateStatusEntity>>(Event.Loading)
    val getEmailAuthenticateStatusResponse = _getEmailAuthenticateStatusResponse.asStateFlow()

    private val _findPasswordResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val findPasswordResponse = _findPasswordResponse.asStateFlow()

    internal var email = savedStateHandle.getStateFlow(key = EMAIL, initialValue = "")

    var newPassword = mutableStateOf("")
        private set

    internal fun getEmailAuthenticateStatus() = viewModelScope.launch {
       getEmailAuthenticateStatusUseCase(
           email = email.value
       ).onSuccess {
           it.catch {remoteError ->
               _getEmailAuthenticateStatusResponse.value = remoteError.errorHandling()
           }.collect { response ->
               _getEmailAuthenticateStatusResponse.value = Event.Success(data = response)
           }
       }.onFailure { error ->
           _getEmailAuthenticateStatusResponse.value = error.errorHandling()
       }
    }

    internal fun sendLinkToEmail() = viewModelScope.launch {
        sendLinkToEmailUseCase(
            body = SendLinkToEmailParam(
                email = email.value
            )
        ).onSuccess {
            it.catch {remoteError ->
                _sendLinkToEmailResponse.value = remoteError.errorHandling()
            }.collect {
                _sendLinkToEmailResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _sendLinkToEmailResponse.value = error.errorHandling()
        }
    }

    internal fun findPassword() = viewModelScope.launch {
        findPasswordUseCase(
            body = FindPasswordParam(
                email = email.value,
                newPassword = newPassword.value
            )
        ).onSuccess {
            it.catch {remoteError ->
                _findPasswordResponse.value = remoteError.errorHandling()
            }.collect {
                _findPasswordResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _findPasswordResponse.value = error.errorHandling()
        }
    }

    internal fun onEmailChange(value: String) { savedStateHandle[EMAIL] = value }
}
