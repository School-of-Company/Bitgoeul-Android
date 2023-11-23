package com.msg.design_system.component.description

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun ContentDescriptionText(
    maxLines: Int,
    text: String,
    modifier : Modifier = Modifier,
) {
    BitgoeulAndroidTheme { colors, type ->
        Text(
            text = text,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = colors.G2,
            style = type.bodySmall,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}