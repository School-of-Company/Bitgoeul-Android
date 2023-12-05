package com.example.my_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.textfield.PasswordTextField
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.util.checkPasswordRegex

@Composable
fun PasswordChangeScreen(
    modifier: Modifier = Modifier
) {
    val currentPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val checkPassword = remember { mutableStateOf("") }

    val isWrongPassword = remember { mutableStateOf(false) }
    val isSamePassword = remember { mutableStateOf(true) }

    BitgoeulAndroidTheme { colors, typography ->
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
                    onClickLink = {},
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
                    onClickLink = {},
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
                    onClickLink = {},
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

                }
                Spacer(modifier = modifier.height(56.dp))
            }
        }
    }
}

@Preview
@Composable
fun PasswordChangeScreenPre() {
    PasswordChangeScreen()
}