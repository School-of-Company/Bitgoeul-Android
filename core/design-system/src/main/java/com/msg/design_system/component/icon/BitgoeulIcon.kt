package com.msg.design_system.component.icon

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.msg.design_system.R

@Composable
fun CancelIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_cancel),
        contentDescription = "Delete text in text field",
        modifier = modifier
    )
}

@Composable
fun SeeableIcon(
    modifier: Modifier = Modifier,
    disable: Boolean = true
) {
    Image(
        painter = painterResource(id = if (disable) R.drawable.ic_seeable_disable else R.drawable.ic_seeable_enable),
        contentDescription = if (disable) "Can't see password on text field" else "Can see password on text field",
        modifier = modifier
    )
}