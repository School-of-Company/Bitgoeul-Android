package com.bitgoeul.email

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bitgoeul.email.viewmodel.EmailViewModel
import com.msg.design_system.R
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun InputNewPasswordRoute(
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit,
    viewModel: EmailViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    InputNewPasswordScreen(
        onBackClicked = onBackClicked,
        onNextClicked = { currentPassword, newPassword ->
            viewModel.currentPassword.value = currentPassword
            viewModel.newPassword.value = newPassword
            viewModel.changePassword()
            onNextClicked()
        },
    )
}

@Composable
fun InputNewPasswordScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onNextClicked: (String, String) -> Unit,
) {
    val currentPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }

    BitgoeulAndroidTheme { color, typography ->
        Surface {
            Column(
                modifier = modifier
                    .background(color = color.WHITE)
                    .padding(horizontal = 28.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = modifier.height(20.dp))

                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = stringResource(id = R.string.go_back)
                ) {
                    onBackClicked()
                }

                Spacer(modifier = modifier.height(16.dp))

                Text(
                    text = "만나서 반가워요!",
                    style = typography.titleLarge,
                    color = color.BLACK
                )

                Text(
                    text = "어디서 오셨나요?",
                    style = typography.bodySmall,
                    color = color.G2
                )

                Spacer(modifier = modifier.height(32.dp))


                DefaultTextField(
                    modifier = modifier.fillMaxWidth(),
                    onValueChange = { inputCurrentPassword ->
                        currentPassword.value = inputCurrentPassword
                    },
                    errorText = "",
                    isDisabled = false,
                    isError = false,
                    isLinked = false,
                    isReverseTrailingIcon = false,
                    onClickButton = {},
                    placeholder = "8~24자 영문, 숫자, 특수문자 1개 이상",
                    visualTransformationState = true
                )

                Spacer(modifier = modifier.height(16.dp))

                DefaultTextField(
                    modifier = modifier.fillMaxWidth(),
                    onValueChange = { inputNewPassword ->
                        newPassword.value = inputNewPassword
                    },
                    errorText = "",
                    isDisabled = false,
                    isError = false,
                    isLinked = false,
                    isReverseTrailingIcon = false,
                    onClickButton = {},
                    placeholder = "비밀번호 확인",
                    visualTransformationState = true
                )

                Spacer(modifier = modifier.weight(1f))

                BitgoeulButton(
                    text = "다음으로",
                    modifier = Modifier
                        .padding(bottom = 14.dp)
                        .fillMaxWidth()
                        .height(52.dp),
                    state = if(currentPassword.value.isNotEmpty() && newPassword.value.isNotEmpty()) ButtonState.Enable else ButtonState.Disable,
                    onClick = {
                        onNextClicked(currentPassword.value, newPassword.value)
                    }
                )
            }
        }
    }
}