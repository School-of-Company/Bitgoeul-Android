package com.msg.design_system.component.topbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun DetailSettingTopBar(
    modifier: Modifier = Modifier,
    text: String,
    onBackClicked: () -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                style = typography.titleSmall,
                color = colors.BLACK
            )
            Spacer(modifier = modifier.weight(1f))
            IconButton(
                modifier = modifier.size(24.dp),
                content = { CloseIcon() },
                onClick = onBackClicked
            )
        }
    }
}