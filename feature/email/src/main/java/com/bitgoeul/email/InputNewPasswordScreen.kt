package com.bitgoeul.email

import androidx.activity.ComponentActivity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
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
import com.msg.ui.makeToast

@Composable
internal fun InputNewPasswordRoute(
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit,
    viewModel: EmailViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val focusManager = LocalFocusManager.current

    InputNewPasswordScreen(
        onBackClicked = onBackClicked,
        focusManager = focusManager,
        onNextClicked = { newPassword ->
            viewModel.newPassword.value = newPassword
            viewModel.findPassword()
            onNextClicked()
        },
    )
}

@Composable
internal fun InputNewPasswordScreen(
    modifier: Modifier = Modifier,
    focusManager: FocusManager,
    onBackClicked: () -> Unit,
    onNextClicked: (String) -> Unit,
) {
    val passwordPattern = Regex("^(?=.*[A-Za-z0-9])[A-Za-z0-9!@#$%^&*]{8,24}$")
    val firstInputPassword = remember { mutableStateOf("") }
    val secondInputPassword = remember { mutableStateOf("") }
    val context = LocalContext.current

    CompositionLocalProvider(LocalFocusManager provides focusManager) {
        BitgoeulAndroidTheme { color, typography ->
            Surface {
                Column(
                    modifier = modifier
                        .background(color = color.WHITE)
                        .padding(horizontal = 28.dp)
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures {
                                focusManager.clearFocus()
                            }
                        }
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
                        onValueChange = { inputPassword ->
                            firstInputPassword.value = inputPassword
                        },
                        errorText = "비밀번호는 8~24자 영문, 숫자, 특수문자 1개 이상이어야 합니다.",
                        isDisabled = false,
                        isError = !passwordPattern.matches(firstInputPassword.value) && firstInputPassword.value.isNotEmpty(),
                        isLinked = false,
                        isReverseTrailingIcon = false,
                        onButtonClicked = {},
                        placeholder = "8~24자 영문, 숫자, 특수문자 1개 이상",
                        visualTransformationState = true
                    )

                    Spacer(modifier = modifier.height(16.dp))

                    DefaultTextField(
                        modifier = modifier.fillMaxWidth(),
                        onValueChange = { inputPassword ->
                            secondInputPassword.value = inputPassword
                        },
                        errorText = "비밀번호가 일치하지 않습니다.",
                        isDisabled = false,
                        isError = firstInputPassword.value != secondInputPassword.value,
                        isLinked = false,
                        isReverseTrailingIcon = false,
                        onButtonClicked = {},
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
                        state = if (firstInputPassword.value.isNotEmpty() && secondInputPassword.value.isNotEmpty()) ButtonState.Enable else ButtonState.Disable,
                        onClicked = {
                            if (passwordPattern.matches(firstInputPassword.value)) {
                                onNextClicked(secondInputPassword.value)
                            } else {
                                makeToast(context, "비밀번호는 8~24자 영문, 숫자, 특수문자 1개 이상이어야 합니다.")
                            }
                        }
                    )
                }
            }
        }
    }
}