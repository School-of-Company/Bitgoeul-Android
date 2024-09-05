package com.msg.certification

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.msg.certification.component.AddAcquisitionDateSection
import com.msg.certification.component.AddCertificationSection
import com.msg.certification.viewmodel.CertificationViewModel
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.modifier.padding.paddingHorizontal
import com.msg.design_system.component.topbar.DetailSettingTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.ui.DevicePreviews
import com.msg.ui.makeToast
import com.msg.ui.util.toKoreanFormat
import java.time.LocalDate

@Composable
internal fun AddCertificationScreenRoute(
    viewModel: CertificationViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onBackClicked: () -> Unit,
    onAddClicked: () -> Unit
) {
    val selectedTitle by viewModel.selectedTitle.collectAsStateWithLifecycle()

    AddCertificationScreen(
        selectedName = selectedTitle,
        selectedDate = viewModel.selectedDate.value,
        onSelectedNameChange = viewModel::onSelectedTitleChange,
        onBackClicked = onBackClicked,
        onAddClicked = { name, acquisitionDate ->
            viewModel.selectedCertificationId.value?.let {
                viewModel.editCertification(name = name, acquisitionDate = acquisitionDate)
            } ?: viewModel.writeCertification(name = name, acquisitionDate = acquisitionDate)
            onAddClicked()
        }
    )
}

@Composable
internal fun AddCertificationScreen(
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current,
    selectedName: String,
    onSelectedNameChange: (String) -> Unit,
    onBackClicked: () -> Unit,
    selectedDate: LocalDate?,
    onAddClicked: (name: String, acquisitionDate: LocalDate) -> Unit
) {
    val (isDate, setIsDate) = rememberSaveable { mutableStateOf(selectedDate) }

    BitgoeulAndroidTheme { colors, _ ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                }
        ) {
            val context = LocalContext.current
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .paddingHorizontal(
                        top = 24.dp,
                        bottom = 14.dp,
                        horizontal = 28.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                DetailSettingTopBar(
                    text = "자격증 세부 설정",
                    onBackClicked = onBackClicked
                )
                AddCertificationSection(
                    onValueChange = onSelectedNameChange,
                    onButtonClicked = { onSelectedNameChange("") }
                )
                AddAcquisitionDateSection(
                    onDatePickerQuit = setIsDate,
                    acquisitionDate = isDate?.toKoreanFormat() ?: ""
                )
                Spacer(modifier = modifier.weight(1f))
                BitgoeulButton(
                    modifier = modifier.fillMaxWidth(),
                    text = "자격증 등록",
                    onClicked = {
                        if (selectedName.isBlank()) {
                            makeToast(context, "자격증 이름을 입력해주세요")
                        } else if (isDate == null) {
                            makeToast(context, "취득일을 입력해주세요")
                        } else {
                            onAddClicked(selectedName, isDate)
                        }
                    }
                )
            }
        }
    }
}


@DevicePreviews
@Composable
fun AddCertificationScreenPre() {
    AddCertificationScreen(
        onBackClicked = {},
        onAddClicked = { _, _ -> },
        selectedName = "",
        selectedDate = null,
        focusManager = LocalFocusManager.current,
        onSelectedNameChange = {},
    )
}