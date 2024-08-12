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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.my_page.viewmodel.MyPageViewModel
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.screen.SuccessScreen
import com.msg.design_system.component.textfield.PasswordTextField
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.util.checkPasswordRegex

@Composable
internal fun PasswordChangeRoute(
    onSuccessScreenButtonClicked: () -> Unit,
    onBackClicked: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {

    PasswordChangeScreen(
        onPasswordChangeClicked = { currentPassword, newPassword ->
            viewModel.changePassword(
                currentPassword = currentPassword,
                newPassword = newPassword
            )
        },
        onSuccessScreenButtonClicked = onSuccessScreenButtonClicked,
        onBackClicked = onBackClicked
    )
}

@Composable
internal fun PasswordChangeScreen(
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current,
    onPasswordChangeClicked: (currentPassword: String, newPassword: String) -> Unit,
    onSuccessScreenButtonClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val currentPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val checkPassword = remember { mutableStateOf("") }

    val isWrongPassword = remember { mutableStateOf(false) }
    val isSamePassword = remember { mutableStateOf(true) }

    val showSuccessScreen = remember { mutableStateOf(false) }

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
                    text = "돌아가기"
                ) {
                    onBackClicked()
                }
                Spacer(modifier = modifier.height(16.dp))
                Column(
                    modifier = modifier.padding(horizontal = 28.dp)
                ) {
                    Text(
                        text = "비밀번호 변경",
                        style = typography.titleLarge,
                        color = colors.BLACK
                    )
                    Spacer(modifier = modifier.height(54.dp))
                    PasswordTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = "현재 비밀번호 입력",
                        errorText = "비밀번호가 일치하지 않습니다",
                        onValueChange = {
                            currentPassword.value = it
                        },
                        onLinkClicked = {},
                        isError = isWrongPassword.value,
                        isLinked = false,
                        isDisabled = false
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    PasswordTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = "새 비밀번호 입력",
                        errorText = "비밀번호는 8~24 영어 + 숫자  + 특수문자 로 해주세요",
                        onValueChange = {
                            newPassword.value = it
                        },
                        onLinkClicked = {},
                        isError = newPassword.value.checkPasswordRegex(),
                        isLinked = false,
                        isDisabled = false
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    PasswordTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeholder = "새 비밀번호 확인",
                        errorText = "비밀번호가 일치하지 않습니다",
                        onValueChange = {
                            checkPassword.value = it
                            isSamePassword.value = newPassword.value == checkPassword.value
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
                        text = "변경하기"
                    ) {
                        onPasswordChangeClicked(currentPassword.value, newPassword.value)
                        showSuccessScreen.value = true
                    }
                    Spacer(modifier = modifier.height(56.dp))
                }
            }
            if (showSuccessScreen.value) {
                SuccessScreen(
                    modifier = modifier,
                    title = "비밀번호 변경 완료",
                    content = "비밀번호 변경을 성공적으로 완료했습니다.",
                    buttonText = "돌아가기"
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
        focusManager = LocalFocusManager.current
    )
}