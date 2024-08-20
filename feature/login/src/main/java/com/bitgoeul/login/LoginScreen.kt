package com.bitgoeul.login

import android.content.pm.ActivityInfo
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bitgoeul.login.navigation.loginRoute
import com.bitgoeul.login.viewmodel.AuthViewModel
import com.msg.common.event.Event
import com.msg.design_system.R
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.component.textfield.LinkText
import com.msg.design_system.component.textfield.PasswordTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.util.LockScreenOrientation
import com.msg.design_system.util.checkEmailRegex
import com.msg.design_system.util.checkPasswordRegex
import com.msg.main.navigation.mainPageRoute
import com.msg.ui.makeToast
import kotlinx.coroutines.launch

@Composable
internal fun LoginRoute(
    onSignUpClicked: () -> Unit,
    onFindPasswordClicked: () -> Unit,
    onLoginClicked: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
) {
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val refreshToken by viewModel.refreshToken.collectAsStateWithLifecycle()
    val refreshTokenTime by viewModel.refreshTokenTime.collectAsStateWithLifecycle()

    LaunchedEffect(Unit, refreshToken, refreshTokenTime) {
        viewModel.validateTokenNavigate()
    }

    LaunchedEffect(key1 = viewModel.navigationEvent) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                AuthViewModel.NavigationEvent.NavigateToMain -> {
                    onLoginClicked()
                }
            }
        }
    }

    LoginScreen(
        focusManager = focusManager,
        onSignUpClicked = onSignUpClicked,
        onFindPasswordClicked = onFindPasswordClicked,
        onLoginClicked = { email, password ->
            viewModel.login(email = email, password = password)
            coroutineScope.launch {
                getLoginData(
                    viewModel = viewModel,
                    onSuccess = {
                        makeToast(context, "로그인에 성공하였습니다.")
                        onLoginClicked()
                    },
                    onFailure = {
                        makeToast(context, "로그인에 실패하였습니다. 다시 시도해주세요")
                    }
                )
            }
        },
    )
}

private suspend fun getLoginData(
    viewModel: AuthViewModel,
    onSuccess: () -> Unit,
    onFailure: () -> Unit,
) {
    viewModel.loginResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                viewModel.saveTokenData(response.data!!)
                onSuccess()
            }

            is Event.BadRequest -> {
                onFailure()
            }

            else -> {
            }
        }
    }
}

@Composable
internal fun LoginScreen(
    focusManager: FocusManager,
    onSignUpClicked: () -> Unit,
    onLoginClicked: (String, String) -> Unit,
    onFindPasswordClicked: () -> Unit,
) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    val isEmailErrorStatus = remember { mutableStateOf(false) }
    val isPasswordErrorStatus = remember { mutableStateOf(false) }
    val isErrorTextShow = remember { mutableStateOf(false) }
    var isTextStatus = ""
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    CompositionLocalProvider(LocalFocusManager provides focusManager) {
        BitgoeulAndroidTheme { color, type ->
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures {
                                focusManager.clearFocus()
                            }
                        }
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(48.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.width(28.dp))
                        Text(
                            modifier = Modifier,
                            text = stringResource(id = R.string.project_name),
                            color = color.BLACK,
                            style = type.titleLarge,
                            fontSize = 30.sp,
                        )
                    }

                    Spacer(modifier = Modifier.height(80.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 28.dp),
                    ) {
                        DefaultTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            placeholder = stringResource(id = R.string.email),
                            errorText = stringResource(id = R.string.error_text_email),
                            onValueChange = {
                                isTextStatus = it
                                emailState.value = it
                            },
                            isError = isEmailErrorStatus.value,
                            onButtonClicked = {
                                isEmailErrorStatus.value = isTextStatus.isNullOrBlank()
                            },
                            isLinked = false,
                            isDisabled = false,
                            isReadOnly = false,
                            isReverseTrailingIcon = false
                        )
                    }

                    if (isErrorTextShow.value) {
                        Spacer(modifier = Modifier.height(0.dp))
                    } else {
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 28.dp),
                    ) {
                        PasswordTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            placeholder = stringResource(id = R.string.password),
                            errorText = stringResource(id = R.string.wrong_password),
                            onValueChange = {
                                passwordState.value = it
                            },
                            onLinkClicked = {
                                onFindPasswordClicked()
                            },
                            isError = isPasswordErrorStatus.value,
                            isLinked = true,
                            linkText = stringResource(id = R.string.find_password),
                            isDisabled = false,
                        )
                    }

                    Spacer(modifier = Modifier.height(180.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 28.dp),
                    ) {
                        BitgoeulButton(
                            text = stringResource(id = R.string.login),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            state = if (emailState.value.checkEmailRegex() && passwordState.value.checkPasswordRegex()) ButtonState.Enable else ButtonState.Disable,
                            onClicked = {
                                onLoginClicked(emailState.value, passwordState.value)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        modifier = Modifier,
                        text = "또는",
                        style = type.labelMedium,
                        color = color.G1,
                        fontSize = 14.sp,
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    LinkText(
                        text = stringResource(id = R.string.sign_up)
                    ) {
                        onSignUpClicked()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPre() {
    LoginScreen(
        onSignUpClicked = { /*TODO*/ },
        onLoginClicked = { _, _ -> },
        onFindPasswordClicked = { /*TODO*/ },
        focusManager = LocalFocusManager.current
    )
}