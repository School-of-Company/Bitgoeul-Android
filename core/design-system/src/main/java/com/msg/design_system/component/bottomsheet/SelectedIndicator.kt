package com.msg.design_system.component.bottomsheet

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun SelectedIndicator(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClicked: (() -> Unit)?
) {
    val interactionSource = remember { mutableStateOf(MutableInteractionSource()) }
    BitgoeulAndroidTheme { colors, _ ->
        Canvas(
            modifier = modifier
                .width(24.dp)
                .height(24.dp)
                .clickable(
                    interactionSource = interactionSource.value,
                    indication = rememberRipple(bounded = false)
                ) {
                    if (onClicked != null && enabled) {
                        onClicked()
                    }
                },
        ) {
            drawCircle(
                color = if (isSelected) colors.P5 else colors.G1,
                style = Stroke(width = 2.dp.toPx())
            )

            drawCircle(
                color = if (isSelected) colors.P5 else Color.Transparent,
                radius = 8.dp.toPx()
            )
        }
    }
}

@Preview
@Composable
fun SelectedIndicatorPre() {
    val selected = remember{ mutableStateOf("0") }
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        SelectedIndicator(isSelected = selected.value == "0", onClicked = { selected.value = "0" })
        Spacer(modifier = Modifier.height(15.dp))
        SelectedIndicator(isSelected = selected.value == "1", onClicked = { selected.value = "1" })
    }
}