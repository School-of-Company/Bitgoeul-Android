package com.example.my_page.viewmodel

import com.msg.model.enumdata.Authority
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.common.errorhandling.errorHandling
import com.msg.common.event.Event
import com.msg.domain.usecase.auth.LogoutUseCase
import com.msg.domain.usecase.auth.WithdrawUseCase
import com.msg.domain.usecase.user.ChangePasswordUseCase
import com.msg.domain.usecase.user.GetMyPageUseCase
import com.msg.model.entity.user.GetMyPageEntity
import com.msg.model.param.user.ChangePasswordParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getMyPageUseCase: GetMyPageUseCase,
    private val withdrawUseCase: WithdrawUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val CURRENT_PASSWORD = "currentPassword"
        private const val NEW_PASSWORD = "newPassword"
        private const val CHECK_PASSWORD = "checkPassword"
    }

    private val _getMyPageResponse = MutableStateFlow<Event<GetMyPageEntity>>(Event.Loading)
    val getMyPageResponse = _getMyPageResponse.asStateFlow()

    private val _getLogoutResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val getLogoutResponse = _getLogoutResponse.asStateFlow()

    private val _getWithdrawResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val getWithdrawResponse = _getWithdrawResponse.asStateFlow()

    private val _getChangePasswordResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val getChangePasswordResponse = _getChangePasswordResponse.asStateFlow()

    var myPageData = mutableStateOf(
        GetMyPageEntity(
            name = "",
            email = "",
            phoneNumber = "",
            authority = Authority.ROLE_USER,
            organization = ""
        )
    )
        private set

    internal var currentPassword = savedStateHandle.getStateFlow(key = CURRENT_PASSWORD, initialValue = "")

    internal var newPassword = savedStateHandle.getStateFlow(key = NEW_PASSWORD, initialValue = "")

    internal var checkPassword = savedStateHandle.getStateFlow(key = CHECK_PASSWORD, initialValue = "")

    internal fun inquiryMyPage() = viewModelScope.launch {
        getMyPageUseCase().onSuccess {
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

    internal fun changePassword(
        currentPassword: String,
        newPassword: String
    ) = viewModelScope.launch {
        changePasswordUseCase(
            body = ChangePasswordParam(
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

    internal fun onCurrentPasswordChange(value: String) { savedStateHandle[CURRENT_PASSWORD] = value }

    internal fun onNewPasswordChange(value: String) { savedStateHandle[NEW_PASSWORD] = value }

    internal fun onCheckPasswordChange(value: String) { savedStateHandle[CHECK_PASSWORD] = value }
}