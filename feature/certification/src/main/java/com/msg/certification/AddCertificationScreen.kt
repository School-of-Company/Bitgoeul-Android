package com.msg.certification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.certification.component.AddAcquisitionDateSection
import com.msg.certification.component.AddCertificationSection
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.topbar.DetailSettingTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.theme.color.BitgoeulColor
import com.msg.ui.DevicePreviews
import com.msg.ui.util.toKoreanFormat
import java.time.LocalDate

@Composable
fun AddCertificationScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onAddClicked: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val date = remember { mutableStateOf<LocalDate?>(null) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = BitgoeulColor.WHITE)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    top = 24.dp,
                    bottom = 14.dp,
                    start = 28.dp,
                    end = 28.dp
                ),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            DetailSettingTopBar(
                text = "자격증 세부 설정",
                onBackClicked = onBackClicked
            )
            AddCertificationSection(
                onValueChange = {
                    name.value = it
                },
                onClickButton = {
                    name.value = ""
                }
            )
            AddAcquisitionDateSection(
                onDatePickerQuit = {
                    date.value = it
                },
                acquisitionDate = date.value?.toKoreanFormat() ?: ""
            )
            Spacer(modifier = modifier.weight(1f))
            BitgoeulButton(
                modifier = modifier.fillMaxWidth(),
                text = "자격증 등록",
                onClick = onAddClicked
            )
        }
    }

}

@DevicePreviews
@Composable
fun AddCertificationScreenPre() {
    AddCertificationScreen(
        onBackClicked = {},
        onAddClicked = {}
    )
}