package com.msg.design_system.component.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun TimeSpinnerPicker(
    modifier: Modifier,
    onHourValueChange: (String) -> Unit,
    onMinuteValueChange: (String) -> Unit,
    onTimeZoneChange: (String) -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        Box(modifier = modifier) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(color = Color.Transparent)
                    .align(Alignment.Center)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                SpinnerPicker(
                    modifier = modifier.weight(1f),
                    itemRange = 1..12,
                    onSelectedItemChange = onHourValueChange
                )

                SpinnerPicker(
                    modifier = modifier.weight(1f),
                    itemRange = 0..59,
                    onSelectedItemChange = onMinuteValueChange
                )
            }
        }
    }
}

@Preview
@Composable
fun TimeSpinnerPickerPre() {
    TimeSpinnerPicker(
        modifier = Modifier,
        onHourValueChange = {},
        onMinuteValueChange = {}
    ) {

    }
}