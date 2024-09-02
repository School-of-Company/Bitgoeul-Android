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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
    viewModel: StudentActivityViewModel = hiltViewModel(),
    onCloseClicked: () -> Unit,
    onApplyClicked: () -> Unit
) {
    val creditList by rememberSaveable { mutableStateOf(listOf("1점", "2점")) }

    ActivityDetailSettingScreen(
        onCloseClicked = onCloseClicked,
        onApplyClicked = { activityDate, credit ->
            viewModel.activityDate.value = activityDate
            viewModel.credit.intValue = credit
            onApplyClicked()
        },
        savedCreditPoint = viewModel.credit.intValue,
        savedActivityDate = viewModel.activityDate.value,
        creditList = creditList
    )
}

@Composable
fun ActivityDetailSettingScreen(
    modifier: Modifier = Modifier,
    onCloseClicked: () -> Unit,
    onApplyClicked: (LocalDate, Int) -> Unit,
    savedCreditPoint: Int,
    savedActivityDate: LocalDate?,
    creditList: List<String>
) {
    val (isCreditPointForShow, isSetCreditPointForShow) = rememberSaveable { mutableStateOf("${savedCreditPoint}점") }
    val creditPoint by rememberSaveable { mutableIntStateOf(if (isCreditPointForShow == "1점") 1 else if (isCreditPointForShow == "2점") 2 else 0) }
    val (isActivityDateForShow, isSetActivityDateForShow) = rememberSaveable { mutableStateOf(savedActivityDate?.toKoreanFormat() ?: "") }
    val (isActivityDate, isSetActivityDate) = rememberSaveable { mutableStateOf(savedActivityDate) }

    BitgoeulAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(colors.WHITE)
                .padding(horizontal = 28.dp)
        ) {
            Spacer(modifier = modifier.height(24.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
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
            Spacer(modifier = modifier.height(28.dp))
            Text(
                text = "활동 날짜",
                style = typography.bodyLarge,
                color = colors.BLACK
            )
            Spacer(modifier = modifier.height(8.dp))
            PickerTextField(
                modifier = modifier.fillMaxWidth(),
                text = isActivityDateForShow.ifEmpty { "활동 날짜 선택" },
                list = listOf(),
                selectedItem = isActivityDateForShow,
                onItemChange = { if (isActivityDateForShow != it) isSetActivityDateForShow(it) else isSetActivityDateForShow("") },
                isDatePicker = true,
                onDatePickerQuit = {
                    if (it != null) {
                        isSetActivityDateForShow(it.toKoreanFormat())
                        isSetActivityDate(it)
                    }
                }
            )
            Spacer(modifier = modifier.height(28.dp))
            Text(
                text = "수여 학점",
                style = typography.bodyLarge,
                color = colors.BLACK
            )
            Spacer(modifier = modifier.height(8.dp))
            PickerTextField(
                modifier = modifier.fillMaxWidth(),
                text = isCreditPointForShow.ifEmpty { "수여 학점 선택" },
                list = creditList,
                selectedItem = isCreditPointForShow,
                onItemChange = { if (isCreditPointForShow != it) isSetCreditPointForShow(it) else isSetCreditPointForShow("") }
            )
            Spacer(modifier = modifier.weight(1f))
            BitgoeulButton(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                text = "적용하기"
            ) {
                isActivityDate?.let { onApplyClicked(it, creditPoint) }
            }
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
        savedCreditPoint = 0,
        creditList = listOf()
    )
}