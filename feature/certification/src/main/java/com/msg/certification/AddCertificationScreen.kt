package com.msg.certification

import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
fun AddCertificationScreenRoute(
    viewModel: CertificationViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onBackClicked: () -> Unit,
    onAddClicked: () -> Unit
) {
    AddCertificationScreen(
        selectedName = viewModel.selectedTitle.value,
        selectedDate = viewModel.selectedDate.value,
        onBackClicked = {
            onBackClicked()
        },
        onAddClicked = { name, acquisitionDate ->
            viewModel.selectedCertificationId.value?.let {
                viewModel.editCertification(name = name, acquisitionDate = acquisitionDate)
            } ?: viewModel.writeCertification(name = name, acquisitionDate = acquisitionDate)
            onAddClicked()
        }
    )
}

@Composable
fun AddCertificationScreen(
    modifier: Modifier = Modifier,
    selectedName: String,
    selectedDate: LocalDate?,
    onBackClicked: () -> Unit,
    onAddClicked: (name: String, acquisitionDate: LocalDate) -> Unit
) {
    val name = remember { mutableStateOf(selectedName) }
    val date = remember { mutableStateOf(selectedDate) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = BitgoeulColor.WHITE)
    ) {
        val context = LocalContext.current
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
                onClick = {
                    if (name.value.isBlank()) {
                        Toast.makeText(context, "자격증 이름을 입력해주세요", Toast.LENGTH_SHORT).show()
                    } else if (date.value == null) {
                        Toast.makeText(context, "취득일을 입력해주세요", Toast.LENGTH_SHORT).show()
                    } else {
                        onAddClicked(name.value, date.value!!)
                    }
                }
            )
        }
    }
}


@DevicePreviews
@Composable
fun AddCertificationScreenPre() {
    AddCertificationScreen(
        onBackClicked = {},
        onAddClicked = {_,_->},
        selectedName = "",
        selectedDate = null
    )
}