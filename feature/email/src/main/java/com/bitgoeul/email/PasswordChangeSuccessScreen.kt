package com.bitgoeul.email

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.msg.design_system.R
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun PasswordChangeSuccessRoute(
    onBackClicked: () -> Unit
) {
    PasswordChangeSuccessScreen(
        onBackClicked = onBackClicked
    )
}
@Composable
fun PasswordChangeSuccessScreen(
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
                Spacer(modifier = modifier.height(48.dp))

                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.project_name),
                    color = color.BLACK,
                    style = typography.titleLarge,
                )

                Spacer(modifier = modifier.height(152.dp))

                Text(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    text = "비밀번호 변경 완료",
                    style = typography.titleMedium,
                    color = color.BLACK
                )

                Text(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    text = "다시 로그인 해야 합니다",
                    style = typography.labelMedium,
                    color = color.G2
                )

                Spacer(modifier = modifier.weight(1f))

                BitgoeulButton(
                    text = "돌아가기",
                    modifier = Modifier
                        .padding(bottom = 14.dp)
                        .fillMaxWidth()
                        .height(52.dp),
                    state = ButtonState.Enable,
                    onClick = {
                        onBackClicked()
                    }
                )
            }
        }
    }
}