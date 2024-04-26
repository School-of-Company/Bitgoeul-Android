package com.bitgoeul.email

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.R
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.util.checkEmailRegex
import com.msg.design_system.util.checkPasswordRegex

@Composable
fun InputEmailScreen(
    modifier: Modifier = Modifier
) {
    BitgoeulAndroidTheme { color, typography ->
        Surface {
            Column(
                modifier = modifier
                    .padding(horizontal = 28.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = modifier.height(20.dp))

                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = stringResource(id = R.string.go_back)
                ) {
                    // TODO onBackClicked() 추가하기
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
                    onValueChange = { },
                    errorText = "",
                    isDisabled = false,
                    isError = false,
                    isLinked = false,
                    isReverseTrailingIcon = false,
                    onClickButton = {},
                    placeholder = stringResource(id = R.string.email)
                )

                Spacer(modifier = modifier.weight(1f))

                BitgoeulButton(
                    text = "다음으로",
                    modifier = Modifier
                        .padding(bottom = 14.dp)
                        .fillMaxWidth()
                        .height(52.dp),
                    state = ButtonState.Disable,
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun InputEmailScreenPre() {
    InputEmailScreen()
}