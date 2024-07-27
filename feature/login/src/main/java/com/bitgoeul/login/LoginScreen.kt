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
import androidx.compose.runtime.saveable.rememberSaveable
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
    val emailState by viewModel.email.collectAsStateWithLifecycle()
    val passwordState by viewModel.password.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val navigateRoute by viewModel.navigateRoute.collectAsStateWithLifecycle()
    val refreshToken by viewModel.refreshToken.collectAsStateWithLifecycle()
    val refreshTokenTime by viewModel.refreshTokenTime.collectAsStateWithLifecycle()

    LaunchedEffect(Unit, refreshToken, refreshTokenTime) {
        viewModel.validateTokenNavigate()
    }

    LaunchedEffect(navigateRoute) {
        when (navigateRoute) {
            loginRoute -> {}
            mainPageRoute -> { onLoginClicked() }
        }
    }

    LoginScreen(
        email = emailState,
        password = passwordState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
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
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    focusManager: FocusManager = LocalFocusManager.current,
    onSignUpClicked: () -> Unit,
    onLoginClicked: (String, String) -> Unit,
    onFindPasswordClicked: () -> Unit,
) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    val (isEmailErrorStatus, setIsEmailErrorStatus) = rememberSaveable { mutableStateOf(false) }
    val isPasswordErrorStatus by rememberSaveable{ mutableStateOf(false) }
    val isErrorTextShow by rememberSaveable { mutableStateOf(false) }

    BitgoeulAndroidTheme { color, type ->
        Surface {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures {
                            focusManager.clearFocus()
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.height(48.dp))

                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = modifier.width(28.dp))
                    Text(
                        modifier = modifier,
                        text = stringResource(id = R.string.project_name),
                        color = color.BLACK,
                        style = type.titleLarge,
                        fontSize = 30.sp,
                    )
                }

                Spacer(modifier = modifier.height(80.dp))

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp),
                ) {
                    DefaultTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        placeholder = stringResource(id = R.string.email),
                        errorText = stringResource(id = R.string.error_text_email),
                        onValueChange = onEmailChange,
                        isError = isEmailErrorStatus,
                        onButtonClicked = { setIsEmailErrorStatus(email.isBlank()) },
                        isLinked = false,
                        isDisabled = false,
                        isReadOnly = false,
                        isReverseTrailingIcon = false
                    )
                }

                if (isErrorTextShow) {
                    Spacer(modifier = modifier.height(0.dp))
                } else {
                    Spacer(modifier = modifier.height(16.dp))
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp),
                ) {
                    PasswordTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        placeholder = stringResource(id = R.string.password),
                        errorText = stringResource(id = R.string.wrong_password),
                        onValueChange = onPasswordChange,
                        onLinkClicked = onFindPasswordClicked,
                        isError = isPasswordErrorStatus,
                        isLinked = true,
                        linkText = stringResource(id = R.string.find_password),
                        isDisabled = false,
                    )
                }

                Spacer(modifier = modifier.height(180.dp))

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp),
                ) {
                    BitgoeulButton(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        text = stringResource(id = R.string.login),
                        state = if (email.checkEmailRegex() && password.checkPasswordRegex()) ButtonState.Enable else ButtonState.Disable,
                        onClicked = {
                            onLoginClicked(email, password)
                        }
                    )
                }

                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = "또는",
                    style = type.labelMedium,
                    color = color.G1,
                    fontSize = 14.sp,
                )

                Spacer(modifier = modifier.height(2.dp))

                LinkText(
                    text = stringResource(id = R.string.sign_up)
                ) {
                    onSignUpClicked()
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
        focusManager = LocalFocusManager.current,
        email = "",
        password = "",
        onEmailChange = {},
        onPasswordChange = {}
    )
}