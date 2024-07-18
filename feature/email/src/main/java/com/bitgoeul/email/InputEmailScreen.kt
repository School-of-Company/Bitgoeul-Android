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

@Composable
internal fun InputEmailRoute(
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit,
    viewModel: EmailViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val focusManager = LocalFocusManager.current

    InputEmailScreen(
        focusManager = focusManager,
        onBackClicked = onBackClicked,
        onNextClicked = { email ->
            viewModel.email.value = email
            viewModel.sendLinkToEmail()
            onNextClicked()
        }
    )
}

@Composable
internal fun InputEmailScreen(
    modifier: Modifier = Modifier,
    focusManager: FocusManager,
    onBackClicked: () -> Unit,
    onNextClicked: (String) -> Unit,
) {
    val email = remember { mutableStateOf("") }

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
                        text = stringResource(id = R.string.find_password),
                        style = typography.titleLarge,
                        color = color.BLACK
                    )

                    Text(
                        text = stringResource(id = R.string.email_authentication_process),
                        style = typography.bodySmall,
                        color = color.G2
                    )

                    Spacer(modifier = modifier.height(32.dp))

                    DefaultTextField(
                        modifier = modifier.fillMaxWidth(),
                        onValueChange = { inputEmail ->
                            email.value = inputEmail
                        },
                        errorText = "",
                        isDisabled = false,
                        isError = false,
                        isLinked = false,
                        isReverseTrailingIcon = false,
                        onButtonClicked = {},
                        placeholder = stringResource(id = R.string.email)
                    )

                    Spacer(modifier = modifier.weight(1f))

                    BitgoeulButton(
                        text = "다음으로",
                        modifier = Modifier
                            .padding(bottom = 14.dp)
                            .fillMaxWidth()
                            .height(52.dp),
                        state = if (email.value.isNotEmpty()) ButtonState.Enable else ButtonState.Disable,
                        onClicked = {
                            onNextClicked(email.value)
                        }
                    )
                }
            }
        }
    }
}