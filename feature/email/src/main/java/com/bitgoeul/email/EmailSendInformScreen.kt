package com.bitgoeul.email

import com.msg.model.entity.email.GetEmailAuthenticateStatusEntity
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.bitgoeul.email.viewmodel.EmailViewModel
import com.msg.common.event.Event
import com.msg.design_system.R
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.ui.makeToast
import kotlinx.coroutines.launch

@Composable
internal fun EmailSendInformRoute(
    onBackClicked: () -> Unit,
    onMoveNewPasswordClicked: () -> Unit,
    viewModel: EmailViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val context = LocalContext.current
    val activity = LocalContext.current as ComponentActivity
    val coroutineScope = rememberCoroutineScope()
    DisposableEffect(activity) {
        val observer = object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
                viewModel.getEmailAuthenticateStatus()
                coroutineScope.launch {
                    getEmailAuthenticateStatus(
                        viewModel = viewModel,
                        onSuccess = { response ->
                            if (response.isAuthentication) {
                                onMoveNewPasswordClicked()
                                makeToast(context, "이메일 인증에 성공했습니다.")
                            }
                            if (!response.isAuthentication) {
                                makeToast(context, "이메일 인증에 실패했습니다.")
                            }
                        }
                    )
                }
            }
        }
        activity.lifecycle.addObserver(observer)
        onDispose {
            activity.lifecycle.removeObserver(observer)
        }
    }

    EmailSendInformScreen(
        onBackClicked = onBackClicked,
        emailText = viewModel.email.value
    )
}




private suspend fun getEmailAuthenticateStatus(
    viewModel: EmailViewModel,
    onSuccess: (data: GetEmailAuthenticateStatusEntity) -> Unit,
) {
    viewModel.getEmailAuthenticateStatusResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            else -> {}
        }
    }
}

@Composable
internal fun EmailSendInformScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    emailText: String,
) {
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

                Spacer(modifier = modifier.weight(1f))

                Text(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    text = emailText + "으로",
                    style = typography.labelMedium,
                    color = color.G2
                )

                Text(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    text = "확인 이메일 발송됨",
                    style = typography.titleMedium,
                    color = color.BLACK
                )

                Spacer(modifier = modifier.weight(1f))

            }

        }
    }
}