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
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
import java.time.LocalDateTime
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
    ModalBottomSheet(
        onDismissRequest = {
            onQuit()
        },

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
                Spacer(modifier = Modifier.height(72.dp))
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LectureFilterBottomSheet(
    onQuit: () -> Unit,
    isVisible: Boolean,
) {
    val isChecked = remember { mutableStateListOf(false, false) }

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (isVisible) {
        BitgoeulAndroidTheme { colors, type ->
            ModalBottomSheet(
                onDismissRequest = {
                    onQuit()
                },
                sheetState = bottomSheetState
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 28.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.filter),
                        modifier = Modifier
                            .width(35.dp)
                            .height(26.dp),
                        color = colors.BLACK,
                        style = type.bodySmall,
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = stringResource(id = R.string.lecture_category),
                        modifier = Modifier
                            .width(67.dp)
                            .height(26.dp),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(vertical = 8.dp, horizontal = 13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BitGoeulCheckBox(
                            checked = isChecked[0],
                            onCheckedChange = { isChecked[0] = it },
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp),
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = stringResource(id = R.string.mutual_credit_recognition_curriculum),
                            modifier = Modifier
                                .width(139.dp),
                            color = colors.BLACK,
                            style = type.bodySmall,
                            fontSize = 16.sp
                        )
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(vertical = 8.dp, horizontal = 13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BitGoeulCheckBox(
                            checked = isChecked[1],
                            onCheckedChange = { isChecked[1] = it },
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp),
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = stringResource(id = R.string.university_visit_program),
                            modifier = Modifier
                                .width(111.dp),
                            color = colors.BLACK,
                            style = type.bodySmall,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    BitgoeulButton(
                        text = stringResource(id = R.string.apply),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        state = ButtonState.Enable
                    ) {
                        // 클릭시 필터 적용 시키기
                    }

                    Box(
                        modifier = Modifier
                            .height(38.dp)
                            .fillMaxWidth()
                    )
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
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    })
    val sheetState = rememberModalBottomSheetState()

    val selectedDate = datePickerState.selectedDateMillis?.convertMillisToDate()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalDateTimePickerBottomSheet(
    onQuit: (LocalDateTime?) -> Unit
) {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    })
    val selectedDate = datePickerState.selectedDateMillis?.convertMillisToDateTime()

    BitgoeulAndroidTheme { colors, typography ->
        ModalBottomSheet(
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

fun Long?.convertMillisToDateTime(): LocalDateTime? {
    return this?.let {
        LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
    }
}

@Preview
@Composable
fun BottomSheetPre() {
    TimePickerBottomSheet(
        onQuit = {},
    )
}