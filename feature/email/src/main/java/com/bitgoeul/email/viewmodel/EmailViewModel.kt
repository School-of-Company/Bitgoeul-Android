package com.bitgoeul.email.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitgoeul.email.util.Event
import com.bitgoeul.email.util.errorHandling
import com.msg.domain.activity.ChangePasswordUseCase
import com.msg.domain.auth.FindPasswordUseCase
import com.msg.domain.email.GetEmailAuthenticateStatusUseCase
import com.msg.domain.email.SendLinkToEmailUseCase
import com.msg.model.remote.request.auth.FindPasswordRequest
import com.msg.model.remote.request.email.SendLinkToEmailRequest
import com.msg.model.remote.request.user.ChangePasswordRequest
import com.msg.model.remote.response.email.GetEmailAuthenticateStatusResponse
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
) : ViewModel() {

    private val _sendLinkToEmailResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val sendLinkToEmailResponse = _sendLinkToEmailResponse.asStateFlow()

    private val _getEmailAuthenticateStatusResponse = MutableStateFlow<Event<GetEmailAuthenticateStatusResponse>>(Event.Loading)
    val getEmailAuthenticateStatusResponse = _getEmailAuthenticateStatusResponse.asStateFlow()

    private val _findPasswordResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val findPasswordResponse = _findPasswordResponse.asStateFlow()

    var email = mutableStateOf("")
        private set

    var newPassword = mutableStateOf("")
        private set

    fun getEmailAuthenticateStatus() = viewModelScope.launch {
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

    fun sendLinkToEmail() = viewModelScope.launch {
        sendLinkToEmailUseCase(
            body = SendLinkToEmailRequest(
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

    fun findPassword() = viewModelScope.launch {
        findPasswordUseCase(
            body = FindPasswordRequest(
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
}
