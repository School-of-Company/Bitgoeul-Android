package com.bitgoeul.inquiry.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.G2ColorFilterIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun InquiryFilterButton(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
) {
    BitgoeulAndroidTheme { colors, _ ->
        Box(
            modifier = modifier
                .clickable {
                    onClicked()
                }
                .border(shape = RoundedCornerShape(8.dp), border =  BorderStroke(1.dp, color = colors.G2))
                .background(color = colors.WHITE)
                .padding(horizontal = 10.dp, vertical = 15.dp),
        ) {
            G2ColorFilterIcon()
        }
    }
}

@Preview
@Composable
fun InquiryFilterButtonPre() {
    InquiryFilterButton {}
}