package com.msg.lecture.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.msg.design_system.R
import com.msg.design_system.component.bottomsheet.DatePickerBottomSheet
import com.msg.design_system.component.bottomsheet.TimePickerBottomSheet
import com.msg.design_system.component.icon.DeleteIcon
import com.msg.design_system.component.icon.PickerArrowIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.model.lecture.LectureDates
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun LectureAttendanceDatePicker(
    modifier: Modifier,
    lectureAttendanceDateText: String,
    lectureStartTimeText: String,
    lectureEndTimeText: String,
    lectureDates: List<LectureDates>,
    onLectureDatesChanged: (index: Int, item: LectureDates) -> Unit,
    onAddLectureDatesClicked: () -> Unit,
    onDatePickerQuit: (LocalDate?) -> Unit = {},
    onStartTimePickerQuit: (LocalTime?) -> Unit = {},
    onEndTimePickerQuit: (LocalTime?) -> Unit = {}
) {
    val isLectureAttendanceFocused = remember { mutableStateOf(false) }
    val isLectureStartTimeFocused = remember { mutableStateOf(false) }
    val isLectureEndTimeFocused = remember { mutableStateOf(false) }
    val showDeleteIcon = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        LazyColumn(
            modifier = modifier
                .wrapContentSize()
                .background(Color.Transparent)
        ) {
            itemsIndexed(lectureDates) { index, item ->
                if (showDeleteIcon.value) {
                    Spacer(modifier = modifier.height(20.dp))
                }
                Row {
                    Row(
                        modifier = modifier
                            .weight(0.85f)
                            .background(
                                color = colors.G9,
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
                            color = colors.G2,
                            style = typography.bodySmall,
                        )

                        PickerArrowIcon(isSelected = false)
                    }

                    if (showDeleteIcon.value) {
                        DeleteIcon(
                            modifier = modifier
                                .weight(0.15f)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    onLectureDatesChanged(index, item)
                                },
                        )
                    }
                }

                Spacer(modifier = modifier.height(12.dp))

                Row {
                    Row(
                        modifier = modifier
                            .weight(1f)
                            .background(
                                color = colors.G9,
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
                            color = colors.G2,
                            style = typography.bodySmall,
                        )

                        PickerArrowIcon(isSelected = false)
                    }

                    Spacer(modifier = modifier.width(8.dp))

                    Row(
                        modifier = modifier
                            .weight(1f)
                            .background(
                                color = colors.G9,
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
                            color = colors.G2,
                            style = typography.bodySmall,
                        )

                        PickerArrowIcon(isSelected = false)
                    }
                }
            }
            item {
                Spacer(modifier = modifier.height(12.dp))

                Text(
                    modifier = modifier.clickable {
                        onAddLectureDatesClicked()
                    },
                    text = stringResource(id = R.string.add_date),
                    color = colors.G2,
                    style = typography.headlineMedium
                )

                Spacer(modifier = modifier.height(28.dp))

                if (isLectureAttendanceFocused.value) {
                    DatePickerBottomSheet { completeDate ->
                        isLectureAttendanceFocused.value = false
                        onDatePickerQuit(completeDate)
                    }
                } else if (isLectureEndTimeFocused.value) {
                    TimePickerBottomSheet { endTime ->
                        isLectureEndTimeFocused.value = false
                        onEndTimePickerQuit(endTime)
                    }
                } else if (isLectureStartTimeFocused.value) {
                    TimePickerBottomSheet { startTime ->
                        isLectureStartTimeFocused.value = false
                        onStartTimePickerQuit(startTime)
                    }
                }
            }
        }

//    BitgoeulAndroidTheme { colors, typography ->
//        var rowItems by remember { mutableStateOf(listOf(1 to false)) }
//
//        Column(
//            modifier = modifier
//                .wrapContentSize()
//                .background(Color.Transparent)
//        ) {
//            rowItems.forEachIndexed { index, (_, showDeleteIcon) ->
//                if (showDeleteIcon) {
//                    Spacer(modifier = modifier.height(20.dp))
//                }
//                Row {
//                    Row(
//                        modifier = modifier
//                            .weight(0.85f)
//                            .background(
//                                color = colors.G9,
//                                shape = RoundedCornerShape(8.dp)
//                            )
//                            .padding(vertical = 15.dp, horizontal = 20.dp)
//                            .clickable {
//                                isLectureAttendanceFocused.value = true
//                            },
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                    ) {
//                        Text(
//                            text = lectureAttendanceDateText,
//                            color = colors.G2,
//                            style = typography.bodySmall,
//                        )
//
//                        PickerArrowIcon(isSelected = false)
//                    }
//
//                    if (showDeleteIcon) {
//                        DeleteIcon(
//                            modifier = modifier
//                                .weight(0.15f)
//                                .align(Alignment.CenterVertically)
//                                .clickable {
//                                    rowItems = rowItems.filterIndexed { i, _ -> i != index }
//                                },
//                        )
//                    }
//                }
//
//                Spacer(modifier = modifier.height(12.dp))
//
//                Row {
//                    Row(
//                        modifier = modifier
//                            .weight(1f)
//                            .background(
//                                color = colors.G9,
//                                shape = RoundedCornerShape(8.dp)
//                            )
//                            .padding(vertical = 15.dp, horizontal = 20.dp)
//                            .clickable {
//                                isLectureStartTimeFocused.value = true
//                            },
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                    ) {
//                        Text(
//                            text = lectureStartTimeText,
//                            color = colors.G2,
//                            style = typography.bodySmall,
//                        )
//
//                        PickerArrowIcon(isSelected = false)
//                    }
//
//                    Spacer(modifier = modifier.width(8.dp))
//
//                    Row(
//                        modifier = modifier
//                            .weight(1f)
//                            .background(
//                                color = colors.G9,
//                                shape = RoundedCornerShape(8.dp)
//                            )
//                            .padding(vertical = 15.dp, horizontal = 20.dp)
//                            .clickable {
//                                isLectureEndTimeFocused.value = true
//                            },
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                    ) {
//                        Text(
//                            text = lectureEndTimeText,
//                            color = colors.G2,
//                            style = typography.bodySmall,
//                        )
//
//                        PickerArrowIcon(isSelected = false)
//                    }
//                }
//            }
//
//            Spacer(modifier = modifier.height(12.dp))
//
//            Text(
//                modifier = modifier.clickable {
//                    rowItems = rowItems + (rowItems.size + 1 to true)
//                },
//                text = stringResource(id = R.string.add_date),
//                color = colors.G2,
//                style = typography.headlineMedium
//            )
//
//            Spacer(modifier = modifier.height(28.dp))
//
//            if (isLectureAttendanceFocused.value) {
//                DatePickerBottomSheet { completeDate ->
//                    isLectureAttendanceFocused.value = false
//                    onDatePickerQuit(completeDate)
//                }
//            } else if (isLectureEndTimeFocused.value) {
//                TimePickerBottomSheet { endTime ->
//                    isLectureEndTimeFocused.value = false
//                    onEndTimePickerQuit(endTime)
//                }
//            } else if (isLectureStartTimeFocused.value) {
//                TimePickerBottomSheet { startTime ->
//                    isLectureStartTimeFocused.value = false
//                    onStartTimePickerQuit(startTime)
//                }
//            }
//        }
    }
}