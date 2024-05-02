package com.msg.design_system.component.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun BitgoeulSubjectText(
    modifier: Modifier = Modifier,
    subjectText: String,
) {
    BitgoeulAndroidTheme { colors, typography ->
        Column {
            Text(
                text = subjectText,
                color = colors.BLACK,
                style = typography.bodyLarge,
            )

            Spacer(modifier = modifier.height(8.dp))
        }
    }
}