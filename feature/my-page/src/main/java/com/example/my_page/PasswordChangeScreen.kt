package com.example.my_page

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.my_page.viewmodel.MyPageViewModel
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.screen.SuccessScreen
import com.msg.design_system.component.textfield.PasswordTextField
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.util.checkPasswordRegex
import com.msg.design_system.R

@Composable
internal fun PasswordChangeRoute(
    onSuccessScreenButtonClicked: () -> Unit,
    onBackClicked: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val currentPassword by viewModel.currentPassword.collectAsStateWithLifecycle()
    val newPassword by viewModel.newPassword.collectAsStateWithLifecycle()
    val checkPassword by viewModel.checkPassword.collectAsStateWithLifecycle()

    PasswordChangeScreen(
        onPasswordChangeClicked = { currentPassword, newPassword ->
            viewModel.changePassword(
                currentPassword = currentPassword,
                newPassword = newPassword
            )
        },
        onSuccessScreenButtonClicked = onSuccessScreenButtonClicked,
        onBackClicked = onBackClicked,
        currentPassword = currentPassword,
        newPassword = newPassword,
        checkPassword = checkPassword,
        onNewPasswordChange = viewModel::onNewPasswordChange,
        onCurrentPasswordChange = viewModel::onCurrentPasswordChange,
        onCheckPasswordChange = viewModel::onCheckPasswordChange
    )
}

@Composable
internal fun PasswordChangeScreen(
    modifier: Modifier = Modifier,
    currentPassword: String,
    newPassword: String,
    checkPassword: String,
    onNewPasswordChange: (String) -> Unit,
    onCurrentPasswordChange: (String) -> Unit,
    onCheckPasswordChange: (String) -> Unit,
    focusManager: FocusManager = LocalFocusManager.current,
    onPasswordChangeClicked: (currentPassword: String, newPassword: String) -> Unit,
    onSuccessScreenButtonClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val isWrongPassword = remember { mutableStateOf(false) }
    val isSamePassword = remember { mutableStateOf(true) }

    val (isShowSuccessScreen, setIsShowSuccessScreen) = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                }
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colors.WHITE)
            ) {
                Spacer(modifier = modifier.height(20.dp))
                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = stringResource(R.string.go_back)
                ) {
                    onBackClicked()
                }
                Spacer(modifier = modifier.height(16.dp))
                Column(
                    modifier = modifier.padding(horizontal = 28.dp)
                ) {
                    Text(
                        text = stringResource(R.string.change_password),
                        style = typography.titleLarge,
                        color = colors.BLACK
                    )
                    Spacer(modifier = modifier.height(54.dp))
                    PasswordTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = stringResource(R.string.current_password),
                        errorText = stringResource(R.string.disagree_password),
                        onValueChange = onCurrentPasswordChange,
                        onLinkClicked = {},
                        isError = isWrongPassword.value,
                        isLinked = false,
                        isDisabled = false
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    PasswordTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = stringResource(R.string.new_password),
                        errorText = "비밀번호는 8~24 영어 + 숫자  + 특수문자 로 해주세요",
                        onValueChange = onNewPasswordChange,
                        onLinkClicked = {},
                        isError = newPassword.checkPasswordRegex(),
                        isLinked = false,
                        isDisabled = false
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    PasswordTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = stringResource(R.string.check_new_password),
                        errorText = stringResource(R.string.disagree_password),
                        onValueChange = {
                            onCheckPasswordChange(it)
                            isSamePassword.value = newPassword == checkPassword
                        },
                        onLinkClicked = {},
                        isError = !isSamePassword.value,
                        isLinked = false,
                        isDisabled = false
                    )
                }
                Spacer(modifier = modifier.weight(1f))
                Column {
                    BitgoeulButton(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 28.dp),
                        text = stringResource(R.string.change)
                    ) {
                        onPasswordChangeClicked(currentPassword, newPassword)
                        setIsShowSuccessScreen(true)
                    }
                    Spacer(modifier = modifier.height(56.dp))
                }
            }
            if (isShowSuccessScreen) {
                SuccessScreen(
                    modifier = modifier,
                    title = stringResource(R.string.finish_change_password),
                    content = stringResource(R.string.success_change_password),
                    buttonText = stringResource(R.string.go_back)
                ) {
                    onSuccessScreenButtonClicked()
                }
            }
        }
    }
}

@Preview
@Composable
fun PasswordChangeScreenPre() {
    PasswordChangeScreen(
        onSuccessScreenButtonClicked = {},
        onPasswordChangeClicked = { _, _ -> },
        onBackClicked = {},
        focusManager = LocalFocusManager.current,
        currentPassword = "",
        onCurrentPasswordChange = {},
        newPassword = "",
        onNewPasswordChange = {},
        checkPassword = "",
        onCheckPasswordChange = {}
    )
}