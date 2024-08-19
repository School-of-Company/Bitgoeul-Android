package com.msg.design_system.component.modifier.padding

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
fun Modifier.paddingHorizontal(
    top: Dp = 0.dp,
    horizontal: Dp = 0.dp,
    bottom: Dp = 0.dp
): Modifier = this then Modifier
    .padding(horizontal = horizontal)
    .padding(
        top = top,
        bottom = bottom
    )