package com.msg.design_system.component.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.msg.design_system.R

@Composable
fun CancelIcon(
    modifier: Modifier = Modifier,
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
    disable: Boolean = true,
) {
    Image(
        painter = painterResource(id = if (disable) R.drawable.ic_seeable_disable else R.drawable.ic_seeable_enable),
        contentDescription = if (disable) "Can't see password on text field" else "Can see password on text field",
        modifier = modifier
    )
}

@Composable
fun GoBackIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = "Go to previous page",
        modifier = modifier
            .width(24.dp)
            .height(24.dp)
    )
}

@Composable
fun FilterIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_filter),
        contentDescription = "apply filter icon",
        modifier = modifier
            .width(24.dp)
            .height(24.dp)
    )
}

@Composable
fun PlusIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_plus_button),
        contentDescription = "plus icon",
        modifier = modifier
            .width(24.dp)
            .height(24.dp)
    )
}