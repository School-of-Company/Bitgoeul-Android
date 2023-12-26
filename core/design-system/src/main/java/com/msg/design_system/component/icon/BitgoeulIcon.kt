package com.msg.design_system.component.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
        contentDescription = "Apply filter icon",
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
        contentDescription = "Plus icon",
        modifier = modifier
            .width(24.dp)
            .height(24.dp)
    )
}

@Composable
fun MainColorSettingIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_main_color_setting),
        contentDescription = "Main Color Setting Icon",
        modifier = modifier
            .width(24.dp)
            .height(24.dp)
    )
}

@Composable
fun CloseIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_close),
        contentDescription = "Close Something Icon",
        modifier = modifier
            .width(24.dp)
            .height(24.dp)
    )
}

@Composable
fun PickerArrowIcon(
    modifier: Modifier = Modifier,
    isSelected: Boolean
) {
    Image(
        painter = painterResource(id = if (isSelected) R.drawable.ic_picker_arrow_selected else R.drawable.ic_picker_arrow),
        contentDescription = "Show Picker Bottom Sheet Icon",
        modifier = modifier
            .width(24.dp)
            .height(24.dp)
    )
}

@Composable
fun SuccessIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_success),
        contentDescription = "Success Icon",
        modifier = modifier
    )
}

@Composable
fun MedicalIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.mipmap.ic_banner_medical),
        contentDescription = "Medical government logo",
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}

@Composable
fun AiIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.mipmap.ic_banner_ai),
        contentDescription = "Ai government logo",
        modifier = modifier
            .height(28.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun EnergyIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.mipmap.ic_banner_energy),
        contentDescription = "Energy government logo",
        modifier = modifier
            .height(28.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun FutureTransportIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.mipmap.ic_banner_future),
        contentDescription = "FutureTransport logo",
        modifier = modifier
            .height(28.dp)
    )
}