package com.msg.design_system.component.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.PickerArrowIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun Picker(
    modifier: Modifier,
    text: String,
    onClicked: (() -> Unit)?,
) {
    BitgoeulAndroidTheme { colors, type ->
        Row(
            modifier = modifier
                .background(
                    color = colors.G9,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .clickable {
                    if(onClicked != null) {
                        onClicked()
                    }
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                color = colors.G2,
                style = type.bodySmall,
            )

            PickerArrowIcon(isSelected = false)
        }
    }
}