package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.PlusWhiteIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun AddLectureDatesButton(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = colors.P5,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 12.dp, horizontal = 20.dp)
                .clickable(
                    onClick = { onAddClick() }
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "날짜 추가",
                style = typography.bodySmall,
                color = colors.WHITE
            )
            Spacer(modifier = modifier.weight(1f))

            PlusWhiteIcon()
        }
    }
}