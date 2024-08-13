package com.bitgoeul.email

import android.content.Context
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bitgoeul.email.viewmodel.EmailViewModel
import com.msg.design_system.R
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.util.checkPasswordRegex
import com.msg.ui.makeToast

@Composable
internal fun InputNewPasswordRoute(
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit,
    viewModel: EmailViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val firstInputPassword by viewModel.firstInputPassword.collectAsStateWithLifecycle()
    val secondInputPassword by viewModel.secondInputPassword.collectAsStateWithLifecycle()

    InputNewPasswordScreen(
        firstInputPassword = firstInputPassword,
        secondInputPassword = secondInputPassword,
        onFirstInputPasswordChange = viewModel::onFirstInputPasswordChange,
        onSecondInputPasswordChange = viewModel::onSecondInputPasswordChange,
        onBackClicked = onBackClicked,
        onNextClicked = { newPassword ->
            viewModel.onNewPasswordChange(newPassword)
            viewModel.findPassword()
            onNextClicked()
        },
    )
}

@Composable
internal fun InputNewPasswordScreen(
    modifier: Modifier = Modifier,
    firstInputPassword: String,
    secondInputPassword: String,
    onFirstInputPasswordChange: (String) -> Unit,
    onSecondInputPasswordChange: (String) -> Unit,
    focusManager: FocusManager = LocalFocusManager.current,
    onBackClicked: () -> Unit,
    onNextClicked: (String) -> Unit,
    context: Context = LocalContext.current
) {
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
                    onValueChange = onFirstInputPasswordChange,
                    errorText = "비밀번호는 8~24자 영문, 숫자, 특수문자 1개 이상이어야 합니다.",
                    isDisabled = false,
                    isError = firstInputPassword.checkPasswordRegex() && firstInputPassword.isNotEmpty(),
                    isLinked = false,
                    isReverseTrailingIcon = false,
                    onButtonClicked = {},
                    placeholder = "8~24자 영문, 숫자, 특수문자 1개 이상",
                    visualTransformationState = true
                )

                Spacer(modifier = modifier.height(16.dp))

                DefaultTextField(
                    modifier = modifier.fillMaxWidth(),
                    onValueChange = onSecondInputPasswordChange,
                    errorText = "비밀번호가 일치하지 않습니다.",
                    isDisabled = false,
                    isError = firstInputPassword != secondInputPassword,
                    isLinked = false,
                    isReverseTrailingIcon = false,
                    onButtonClicked = {},
                    placeholder = "비밀번호 확인",
                    visualTransformationState = true
                )

                Spacer(modifier = modifier.weight(1f))

                BitgoeulButton(
                    modifier = modifier
                        .padding(bottom = 14.dp)
                        .fillMaxWidth()
                        .height(52.dp),
                    text = "다음으로",
                    state = if (firstInputPassword.isNotEmpty() && secondInputPassword.isNotEmpty()) ButtonState.Enable else ButtonState.Disable,
                    onClicked = {
                        if (firstInputPassword.checkPasswordRegex()) {
                            onNextClicked(secondInputPassword)
                        } else {
                            makeToast(context, "비밀번호는 8~24자 영문, 숫자, 특수문자 1개 이상이어야 합니다.")
                        }
                    }
                )
            }
        }
    }
}