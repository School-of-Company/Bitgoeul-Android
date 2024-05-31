package com.msg.student_activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.component.textfield.PickerTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.student_activity.viewmodel.StudentActivityViewModel
import com.msg.ui.util.toKoreanFormat
import java.time.LocalDate

@Composable
fun ActivityDetailSettingRoute(
    onCloseClicked: () -> Unit,
    onApplyClicked: () -> Unit,
    viewModel: StudentActivityViewModel = hiltViewModel()
) {
    ActivityDetailSettingScreen(
        onCloseClicked = onCloseClicked,
        onApplyClicked = { activityDate, credit ->
            viewModel.activityDate.value = activityDate
            viewModel.credit.intValue = credit
            onApplyClicked()
        },
        savedCreditPoint = viewModel.credit.intValue,
        savedActivityDate = viewModel.activityDate.value
    )
}

@Composable
fun ActivityDetailSettingScreen(
    onCloseClicked: () -> Unit,
    onApplyClicked: (LocalDate, Int) -> Unit,
    savedCreditPoint: Int,
    savedActivityDate: LocalDate?
) {
    val creditList = listOf("1점", "2점")

    val creditPointForShow = remember { mutableStateOf("${savedCreditPoint}점") }
    val creditPoint = remember { mutableIntStateOf(if (creditPointForShow.value == "1점") 1 else if (creditPointForShow.value == "2점") 2 else 0) }
    val activityDateForShow = remember { mutableStateOf(savedActivityDate?.toKoreanFormat() ?: "") }
    val activityDate = remember { mutableStateOf(savedActivityDate) }

    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.WHITE)
                .padding(horizontal = 28.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "활동 세부 설정",
                    style = typography.titleSmall,
                    color = colors.BLACK
                )
                IconButton(
                    onClick = onCloseClicked,
                    content = { CloseIcon() }
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "활동 날짜",
                style = typography.bodyLarge,
                color = colors.BLACK
            )
            Spacer(modifier = Modifier.height(8.dp))
            PickerTextField(
                modifier = Modifier.fillMaxWidth(),
                text = activityDateForShow.value.ifEmpty { "활동 날짜 선택" },
                list = listOf(),
                selectedItem = activityDateForShow.value,
                onItemChange = {
                    if (activityDateForShow.value != it) activityDateForShow.value = it else activityDateForShow.value = ""
                },
                isDatePicker = true,
                onDatePickerQuit = {
                    if (it != null) {
                        activityDateForShow.value = it.toKoreanFormat()
                        activityDate.value = it
                    }
                }
            )
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "수여 학점",
                style = typography.bodyLarge,
                color = colors.BLACK
            )
            Spacer(modifier = Modifier.height(8.dp))
            PickerTextField(
                modifier = Modifier.fillMaxWidth(),
                text = creditPointForShow.value.ifEmpty { "수여 학점 선택" },
                list = creditList,
                selectedItem = creditPointForShow.value,
                onItemChange = {
                    if (creditPointForShow.value != it) creditPointForShow.value = it else creditPointForShow.value = ""
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            BitgoeulButton(
                modifier = Modifier.fillMaxWidth(),
                text = "적용하기"
            ) {
                activityDate.value?.let { onApplyClicked(it, creditPoint.value) }
            }
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Preview
@Composable
fun ActivityDetailSettingScreenPre() {
    ActivityDetailSettingScreen(
        onCloseClicked = {},
        onApplyClicked = {_, _ ->},
        savedActivityDate = LocalDate.now(),
        savedCreditPoint = 0
    )
}