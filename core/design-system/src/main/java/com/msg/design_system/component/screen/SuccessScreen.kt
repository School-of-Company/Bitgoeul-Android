package com.msg.design_system.component.screen

import androidx.compose.foundation.background
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
import com.msg.design_system.component.icon.SuccessIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun SuccessScreen(
    modifier: Modifier,
    title: String,
    content: String,
    buttonText: String,
    buttonAction: () -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.height(228.dp))
            SuccessIcon()
            Spacer(modifier = modifier.height(24.dp))
            Text(
                text = title,
                style = typography.titleMedium,
                color = colors.BLACK
            )
            Text(
                text = content,
                style = typography.bodySmall,
                color = colors.G2
            )
            Spacer(modifier = modifier.weight(1f))
            BitgoeulButton(
                modifier = modifier.fillMaxWidth(),
                text = buttonText
            ) {
                buttonAction()
            }
            Spacer(modifier = modifier.height(56.dp))
        }
    }
}