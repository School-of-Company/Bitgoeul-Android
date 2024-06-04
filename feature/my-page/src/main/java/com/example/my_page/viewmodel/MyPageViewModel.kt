package com.example.my_page.viewmodel

import Authority
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_page.util.Event
import com.example.my_page.util.errorHandling
import com.msg.domain.auth.LogoutUseCase
import com.msg.domain.auth.WithdrawUseCase
import com.msg.domain.user.ChangePasswordUseCase
import com.msg.domain.user.InquiryMyPageUseCase
import com.msg.model.remote.request.user.ChangePasswordRequest
import com.msg.model.remote.response.user.InquiryMyPageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val inquiryMyPageUseCase: InquiryMyPageUseCase,
    private val withdrawUseCase: WithdrawUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase
) : ViewModel() {

    private val _getMyPageResponse = MutableStateFlow<Event<InquiryMyPageResponse>>(Event.Loading)
    val getMyPageResponse = _getMyPageResponse.asStateFlow()

    private val _getLogoutResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val getLogoutResponse = _getLogoutResponse.asStateFlow()

    private val _getWithdrawResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val getWithdrawResponse = _getWithdrawResponse.asStateFlow()

    private val _getChangePasswordResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val getChangePasswordResponse = _getChangePasswordResponse.asStateFlow()

    var myPageData = mutableStateOf(
        InquiryMyPageResponse(
            name = "",
            email = "",
            phoneNumber = "",
            authority = Authority.ROLE_USER,
            organization = ""
        )
    )
        private set

    internal fun inquiryMyPage() = viewModelScope.launch {
        inquiryMyPageUseCase().onSuccess {
            it.catch { remoteError ->
                _getMyPageResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getMyPageResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getMyPageResponse.value = error.errorHandling()
        }
    }

    internal fun logout() = viewModelScope.launch {
        logoutUseCase().onSuccess {
            it.catch { remoteError ->
                _getLogoutResponse.value = remoteError.errorHandling()
            }.collect {
                _getLogoutResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _getLogoutResponse.value = error.errorHandling()
        }
    }

    internal fun withdraw() = viewModelScope.launch {
        withdrawUseCase().onSuccess {
            it.catch { remoteError ->
                _getWithdrawResponse.value = remoteError.errorHandling()
            }.collect {
                _getWithdrawResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _getWithdrawResponse.value = error.errorHandling()
        }
    }

    internal fun changePassword(currentPassword: String, newPassword: String) = viewModelScope.launch {
        changePasswordUseCase(
            body = ChangePasswordRequest(
            currentPassword = currentPassword,
            newPassword = newPassword
            )
        ).onSuccess {
            it.catch { remoteError ->
                _getChangePasswordResponse.value = remoteError.errorHandling()
            }.collect {
                _getChangePasswordResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _getChangePasswordResponse.value = error.errorHandling()
        }
    }
}