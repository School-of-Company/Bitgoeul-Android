package com.msg.design_system.component.checkbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.R

@Composable
fun BitGoeulCheckBox(
    modifier: Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Box(
        modifier = modifier.background(color = Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = if (isChecked) {
                painterResource(id = R.drawable.ic_checked_box)
            } else {
                painterResource(id = R.drawable.ic_unchecked_box)
            },
            contentDescription = null,
            modifier = modifier.clickable { onCheckedChange(!isChecked) }
        )
    }
}