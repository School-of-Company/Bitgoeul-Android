package com.bitgoeul.email.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitgoeul.email.util.Event
import com.bitgoeul.email.util.errorHandling
import com.msg.domain.activity.ChangePasswordUseCase
import com.msg.domain.email.GetEmailAuthenticateStatusUseCase
import com.msg.domain.email.SendLinkToEmailUseCase
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
    private val changePasswordUseCase: ChangePasswordUseCase
) : ViewModel() {

    private val _sendLinkToEmailResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val sendLinkToEmailResponse = _sendLinkToEmailResponse.asStateFlow()

    private val _getEmailAuthenticateStatusResponse = MutableStateFlow<Event<GetEmailAuthenticateStatusResponse>>(Event.Loading)
    val getEmailAuthenticateStatusResponse = _getEmailAuthenticateStatusResponse.asStateFlow()

    private val _changePasswordResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val changePasswordResponse = _changePasswordResponse.asStateFlow()

    var email = mutableStateOf("")
        private set

    var currentPassword = mutableStateOf("")
        private set

    var newPassword = mutableStateOf("")
        private set

    var authenticateStatus = mutableStateOf<Boolean?>(false)
        private set

    fun getEmailAuthenticateStatus() = viewModelScope.launch {
       getEmailAuthenticateStatusUseCase(
           email = email.value
       ).onSuccess {
           it.catch {remoteError ->
               _getEmailAuthenticateStatusResponse.value = remoteError.errorHandling()
           }.collect { response ->
               _getEmailAuthenticateStatusResponse.value = Event.Success(data = response)
               authenticateStatus.value = response.isAuthentication
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

    fun changePassword(
    ) = viewModelScope.launch {
        changePasswordUseCase(
            body = ChangePasswordRequest(
                currentPassword = currentPassword.value,
                newPassword = newPassword.value
            )
        ).onSuccess {
            it.catch {remoteError ->
                _changePasswordResponse.value = remoteError.errorHandling()
            }.collect {
                _changePasswordResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _changePasswordResponse.value = error.errorHandling()
        }
    }
}
