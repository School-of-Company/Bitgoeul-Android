package com.msg.design_system.component.bottomsheet

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msg.design_system.R
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.checkbox.BitGoeulCheckBox
import com.msg.design_system.theme.BitgoeulAndroidTheme
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorBottomSheet(
    list: List<String>,
    selectedItem: String,
    itemChange: (value: String) -> Unit,
    onQuit: () -> Unit,
    firstItem: @Composable (() -> Unit) = {},
    lastItem: @Composable (() -> Unit) = {},
) {
    BitgoeulAndroidTheme { colors, _ ->
        ModalBottomSheet(
            onDismissRequest = {
                onQuit()
            },
            containerColor = colors.WHITE

        ) {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 28.dp)
            ) {
                item {
                    firstItem()
                }
                items(list.size) {
                    Selector(
                        value = list[it],
                        isSelected = selectedItem == list[it]
                    ) {
                        itemChange(list[it])
                    }
                }
                item {
                    lastItem()
                }
                item {
                    Spacer(modifier = Modifier.height(72.dp))
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TimePickerBottomSheet(
    modifier: Modifier = Modifier,
    onQuit: (LocalTime) -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState()
    val timePickerState = rememberTimePickerState()
    val selectedTime = LocalTime.of(timePickerState.hour, timePickerState.minute)

    BitgoeulAndroidTheme { colors, _ ->
        ModalBottomSheet(
            modifier = modifier
                .height(330.dp),
            sheetState = bottomSheetState,
            onDismissRequest = {
                onQuit(selectedTime)
                Log.e("selectedTIme", selectedTime.toString())
            },
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                TimeInput(
                    state = timePickerState,
                    colors = TimePickerDefaults.colors(
                        selectorColor = colors.BLACK,
                        containerColor = colors.WHITE,
                        timeSelectorSelectedContainerColor = colors.P5,
                        timeSelectorSelectedContentColor = colors.WHITE,
                        periodSelectorBorderColor = colors.BLACK,
                        periodSelectorSelectedContentColor = colors.WHITE,
                        periodSelectorSelectedContainerColor = colors.P5,
                    ),
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerBottomSheet(
    onQuit: (LocalDate?) -> Unit
) {
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.convertMillisToDate()
    val sheetState = rememberModalBottomSheetState()

    BitgoeulAndroidTheme { colors, typography ->
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { onQuit(selectedDate) }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = colors.WHITE,
                    selectedDayContainerColor = colors.P5,
                    selectedYearContainerColor = colors.P5,
                    todayDateBorderColor = colors.P5,
                    todayContentColor = colors.P5
                ),
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

fun Long.convertMillisToDate(): LocalDate {
    return Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()
}

@Preview
@Composable
fun BottomSheetPre() {
    TimePickerBottomSheet(
        onQuit = {},
    )
}