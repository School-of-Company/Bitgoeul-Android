package com.example.my_page.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.util.BitgoeulIndication

@Composable
fun AccountSettingView(
    modifier: Modifier,
    onPasswordChangeClicked: () -> Unit,
    onLogOutClicked: () -> Unit,
    onWithdrawClicked: () -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Text(
            modifier = modifier.padding(vertical = 20.dp),
            text = "계정 설정",
            style = typography.bodyLarge,
            color = colors.BLACK
        )
        Text(
            modifier = modifier
                .padding(vertical = 20.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = BitgoeulIndication,
                    onClick = onPasswordChangeClicked
                ),
            text = "비밀번호 변경",
            style = typography.bodySmall,
            color = colors.P5
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = colors.G1
        )
        Text(
            modifier = modifier
                .padding(vertical = 20.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = BitgoeulIndication,
                    onClick = onLogOutClicked
                ),
            text = "로그아웃",
            style = typography.bodySmall,
            color = colors.P5
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = colors.G1
        )
        Text(
            modifier = modifier
                .padding(vertical = 20.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = BitgoeulIndication,
                    onClick = onWithdrawClicked
                ),
            text = "회원 탈퇴",
            style = typography.bodySmall,
            color = colors.E5
        )
    }
}