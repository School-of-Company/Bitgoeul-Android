package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.R
import com.msg.design_system.component.bottomsheet.DatePickerBottomSheet
import com.msg.design_system.component.bottomsheet.TimePickerBottomSheet
import com.msg.design_system.component.icon.DeleteIcon
import com.msg.design_system.component.icon.PickerArrowIcon
import com.msg.design_system.component.picker.Picker
import com.msg.design_system.component.textfield.PickerTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun LectureAttendanceDatePicker(
    modifier: Modifier,
    lectureAttendanceDateText: String,
    lectureStartTimeText: String,
    lectureEndTimeText: String,
    isDatePicker: Boolean = false,
    isTimePicker: Boolean = false,
    onDatePickerQuit: (LocalDate?) -> Unit = {},
    onStartTimePickerQuit: (LocalTime?) -> Unit = {},
    onEndTimePickerQuit: (LocalTime?) -> Unit = {}
) {
    val isLectureAttendanceFocused = remember { mutableStateOf(false) }
    val isLectureStartTimeFocused = remember { mutableStateOf(false) }
    val isLectureEndTimeFocused = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        var rowItems by remember { mutableStateOf(listOf(1 to false)) }

        Column(
            modifier = modifier
                .wrapContentSize()
                .background(Color.Transparent)
        ) {
            rowItems.forEachIndexed { _, (_, showDeleteIcon) ->
                if (showDeleteIcon) {
                    Spacer(modifier = modifier.height(20.dp))
                }
                Row {

                    Row(
                        modifier = modifier
                            .weight(0.85f)
                            .background(
                                color = if (isLectureAttendanceFocused.value) colors.P5 else colors.G9,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 15.dp, horizontal = 20.dp)
                            .clickable {
                                isLectureAttendanceFocused.value = true
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = lectureAttendanceDateText,
                            color = if (isLectureAttendanceFocused.value) colors.WHITE else colors.G2,
                            style = typography.bodySmall,
                        )

                        PickerArrowIcon(isSelected = isLectureAttendanceFocused.value)
                    }

                    if (showDeleteIcon) {
                        DeleteIcon(
                            modifier = modifier
                                .weight(0.15f)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    rowItems = rowItems - (rowItems.size to true)
                                },
                        )
                    }
                }
                if (isLectureAttendanceFocused.value) {
                    when {
                        isDatePicker -> {
                            DatePickerBottomSheet { completeDate ->
                                isLectureAttendanceFocused.value = false
                                onDatePickerQuit(completeDate)
                            }
                        }

                        isTimePicker -> {
                            TimePickerBottomSheet {
                                isLectureAttendanceFocused.value = false
                                onStartTimePickerQuit(it)
                            }
                        }
                    }
                }


                Spacer(modifier = modifier.height(12.dp))

                Row {
                    Row(
                        modifier = modifier
                            .weight(1f)
                            .background(
                                color = if (isLectureStartTimeFocused.value) colors.P5 else colors.G9,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 15.dp, horizontal = 20.dp)
                            .clickable {
                                isLectureStartTimeFocused.value = true
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = lectureStartTimeText,
                            color = if (isLectureStartTimeFocused.value) colors.WHITE else colors.G2,
                            style = typography.bodySmall,
                        )

                        PickerArrowIcon(isSelected = isLectureStartTimeFocused.value)
                    }
                    if (isLectureStartTimeFocused.value) {
                        when {
                            isDatePicker -> {
                                DatePickerBottomSheet { completeDate ->
                                    isLectureStartTimeFocused.value = false
                                    onDatePickerQuit(completeDate)
                                }
                            }

                            isTimePicker -> {
                                TimePickerBottomSheet { startTime ->
                                    isLectureStartTimeFocused.value = false
                                    onStartTimePickerQuit(startTime)
                                }
                                if (showDeleteIcon) {
                                    DeleteIcon(
                                        modifier = modifier
                                            .weight(0.15f)
                                            .align(Alignment.CenterVertically)
                                            .clickable {
                                                rowItems = rowItems - (rowItems.size to true)
                                            },
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = modifier.width(8.dp))

                    Row(
                        modifier = modifier
                            .weight(1f)
                            .background(
                                color = if (isLectureEndTimeFocused.value) colors.P5 else colors.G9,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 15.dp, horizontal = 20.dp)
                            .clickable {
                                isLectureEndTimeFocused.value = true
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = lectureEndTimeText,
                            color = if (isLectureEndTimeFocused.value) colors.WHITE else colors.G2,
                            style = typography.bodySmall,
                        )

                        PickerArrowIcon(isSelected = isLectureEndTimeFocused.value)
                    }
                    if (isLectureEndTimeFocused.value) {
                        when {
                            isDatePicker -> {
                                DatePickerBottomSheet { completeDate ->
                                    isLectureEndTimeFocused.value = false
                                    onDatePickerQuit(completeDate)
                                }
                            }

                            isTimePicker -> {
                                TimePickerBottomSheet { endTime ->
                                    isLectureEndTimeFocused.value = false
                                    onEndTimePickerQuit(endTime)
                                }
                                if (showDeleteIcon) {
                                    DeleteIcon(
                                        modifier = modifier
                                            .weight(0.15f)
                                            .align(Alignment.CenterVertically)
                                            .clickable {
                                                rowItems =
                                                    rowItems - (rowItems.size to true)
                                            },
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = modifier.height(12.dp))

            Text(
                modifier = modifier.clickable {
                    rowItems = rowItems + (rowItems.size + 1 to true)
                },
                text = stringResource(id = R.string.add_date),
                color = colors.G2,
                style = typography.headlineMedium
            )
        }

        Spacer(modifier = modifier.height(28.dp))
    }
}

@Preview
@Composable
fun imsiTest() {
    LectureAttendanceDatePicker(
        modifier = Modifier
            .wrapContentSize(),
        lectureAttendanceDateText = "강의 수강 날짜",
        lectureStartTimeText = "강의 시작 시간",
        lectureEndTimeText = "강의 종료 시간"
    )
}