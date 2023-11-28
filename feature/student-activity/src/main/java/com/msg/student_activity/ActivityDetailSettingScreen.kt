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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.component.textfield.PickerTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.ui.util.toKoreanFormat

@Composable
fun ActivityDetailSettingScreen(
    onCloseClick: () -> Unit
) {
    val creditList = listOf("1점", "2점")

    val creditPoint = remember { mutableStateOf("") }
    val activityDate = remember { mutableStateOf("") }

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
                    onClick = onCloseClick,
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
                text = activityDate.value.ifEmpty { "활동 날짜 선택" },
                list = listOf(),
                selectedItem = activityDate.value,
                onItemChange = {
                    if (activityDate.value != it) activityDate.value = it else activityDate.value = ""
                },
                isDatePicker = true,
                onQuit = {
                    if (it != null) {
                        activityDate.value = it.toKoreanFormat()
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
                text = creditPoint.value.ifEmpty { "수여 학점 선택" },
                list = creditList,
                selectedItem = creditPoint.value,
                onItemChange = {
                    if (creditPoint.value != it) creditPoint.value = it else creditPoint.value = ""
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            BitgoeulButton(
                modifier = Modifier.fillMaxWidth(),
                text = "적용하기"
            ) {

            }
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Preview
@Composable
fun ActivityDetailSettingScreenPre() {
    ActivityDetailSettingScreen(
        onCloseClick = {}
    )
}