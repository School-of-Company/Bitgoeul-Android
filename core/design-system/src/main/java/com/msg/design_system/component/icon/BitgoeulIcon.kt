package com.msg.design_system.component.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
    isSelected: Boolean,
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
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_success),
        contentDescription = "Success Icon",
        modifier = modifier
    )
}

@Composable
fun MedicalIcon(
    modifier: Modifier = Modifier,
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
    modifier: Modifier = Modifier,
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
    modifier: Modifier = Modifier,
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
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.mipmap.ic_banner_future),
        contentDescription = "FutureTransport logo",
        modifier = modifier
            .height(28.dp)
    )
}

@Composable
fun GwangjuIcon(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .width(48.dp)
                .height(48.dp),
            painter = painterResource(id = R.mipmap.ic_banner_gwangju),
            contentDescription = "Icon of Gwangju",
            colorFilter = ColorFilter.tint(color = Color(0xffF2F2F2))
        )
        Spacer(modifier = modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.text_gwangju),
            contentDescription = "광주광역시"
        )
    }
}

@Composable
fun OfficeOfEducationIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_banner_education),
        contentDescription = "Gwangju Metropolitan office of education logo"
    )
}

@Composable
fun KebabIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_kebab),
        contentDescription = "Kebab Icon"
    )
}

@Composable
fun PlusWhiteIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_plus_white),
        contentDescription = "White Plus Icon"
    )
}

@Composable
fun MegaphoneIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_megaphone),
        contentDescription = "White Megaphone icon"
    )
}

@Composable
fun HelpIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_help),
        contentDescription = "White question mark icon"
    )
}

@Composable
fun BlackCloseIcon(
    modifier: Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_black_close),
        contentDescription = "Black Close Icon",
        modifier = modifier
    )
}

@Composable
fun SearchIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_bitgoeul_search),
        contentDescription = "Search Icon",
        modifier = modifier
    )
}

@Composable
fun DeleteIcon(
    modifier: Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_delete),
        contentDescription = "Delete Icon",
        modifier = modifier
    )
}
@Composable
fun ChatIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_chat),
        contentDescription = "White chat icon"
    )
}