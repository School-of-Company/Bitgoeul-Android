package com.msg.sign_up

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.BigCheckIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.ui.DevicePreviews

@Composable
internal fun SignupFinishScreenRoute(
    onBackClicked: () -> Unit
) {
    SignUpFinishScreen(
        onBackClicked = onBackClicked
    )
}

@Composable
internal fun SignUpFinishScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.weight(1f))
            BigCheckIcon()
            Spacer(modifier = modifier.height(24.dp))
            Text(
                text = "회원가입 완료",
                style = typography.titleMedium,
                color = colors.BLACK
            )
            Text(
                text = "관리자의 승인을 기다려주세요!",
                style = typography.bodySmall,
                color = colors.G2
            )
            Spacer(modifier = modifier.weight(1f))
            BitgoeulButton(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                text = "돌아가기",
                onClick = onBackClicked
            )
            Spacer(modifier = modifier.height(56.dp))
        }
    }
}

@DevicePreviews
@Composable
fun SignUpFinishScreenPre() {
    SignUpFinishScreen(onBackClicked = {})
}