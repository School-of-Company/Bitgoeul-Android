package com.msg.design_system.component.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun NegativeTag(
    modifier: Modifier = Modifier,
    text: String,
) {
    BitgoeulAndroidTheme { colors, typography ->
        Box(
            modifier = modifier
                .wrapContentSize()
                .background(color = colors.E5, shape = RoundedCornerShape(18.dp))
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Text(
                text = text,
                style = typography.labelMedium,
                color = colors.WHITE
            )
        }
    }
}

@Preview
@Composable
fun NegativeTagPre() {
    NegativeTag(
        text = "답변 대기 중"
    )
}