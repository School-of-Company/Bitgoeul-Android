package com.msg.design_system.component.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun Selector(
    value: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    BitgoeulAndroidTheme { _, typography ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onClick()
                }
        ) {
            Text(
                text = value,
                style = typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            SelectedIndicator(
                isSelected = isSelected,
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = {
                    onClick()
                }
            )
        }
    }
}

@Preview
@Composable
fun selectorPre() {
    Selector(value = "Android", isSelected = true) {

    }
}