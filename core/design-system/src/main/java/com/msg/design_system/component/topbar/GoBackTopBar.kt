package com.msg.design_system.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun GoBackTopBar(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    text: String,
    onClicked: () -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(colors.WHITE)
        ) {
            IconButton(
                onClick = onClicked,
                modifier = modifier
                    .height(24.dp)
                    .width(24.dp)
                    .align(Alignment.CenterVertically)
            ) {
                icon()
            }
            Spacer(
                modifier = modifier.width(7.dp)
            )
            Text(
                modifier = modifier
                    .align(Alignment.CenterVertically),
                text = text,
                style = typography.bodySmall,
            )
        }
    }
}

@Preview
@Composable
fun GoBackTopBarPre() {
    GoBackTopBar(
        icon = { GoBackIcon() },
        text = "돌아가기"
    ) {

    }
}