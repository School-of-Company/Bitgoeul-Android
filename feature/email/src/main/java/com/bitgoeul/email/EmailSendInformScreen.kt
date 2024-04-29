package com.bitgoeul.email

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bitgoeul.email.util.Event
import com.bitgoeul.email.viewmodel.EmailViewModel
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.R
import com.msg.model.remote.response.email.GetEmailAuthenticateStatusResponse

@Composable
fun EmailSendInformRoute(
    onBackClicked: () -> Unit,
    onMoveNewPasswordClick: () -> Unit,
    viewModel: EmailViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.getEmailAuthenticateStatus()
        getEmailAuthenticateStatus(
            viewModel = viewModel,
            onSuccess = { response ->
                if (response.isAuthentication) {
                    onMoveNewPasswordClick()
                } else {
                    Toast.makeText(context, "이메일 인증에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
    EmailSendInformScreen(
        onBackClicked = onBackClicked
    )
}

suspend fun getEmailAuthenticateStatus(
    viewModel: EmailViewModel,
    onSuccess: (data: GetEmailAuthenticateStatusResponse) -> Unit,
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
fun EmailSendInformScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
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
                    text = "s22055@gsm.hs.kr 로",
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