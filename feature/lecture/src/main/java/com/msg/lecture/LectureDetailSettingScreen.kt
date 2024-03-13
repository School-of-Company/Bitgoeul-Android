package com.msg.lecture

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import com.msg.design_system.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.component.icon.PickerArrowIcon
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.LectureSettingTag

@Composable
fun LectureDetailSettingScreen(
    modifier: Modifier = Modifier,
) {
    val lectureType = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    BitgoeulAndroidTheme { colors, type ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(colors.WHITE)
        ) {
            Column(
                modifier = modifier
                    .background(color = colors.WHITE)
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.lecture_detail_setting),
                        color = colors.BLACK,
                        style = type.titleSmall,
                    )

                    CloseIcon()

                }
                Spacer(modifier = modifier.height(28.dp))

                Column(
                    modifier = modifier
                        .verticalScroll(scrollState)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.lecture_category),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    LectureSettingTag(
                        modifier = modifier.clickable {
                            lectureType.value = "상호학점인정교육과정"
                        },
                        lectureType = stringResource(id = R.string.mutual_credit_recognition_curriculum)
                    )

                    Spacer(modifier = modifier.height(16.dp))

                    LectureSettingTag(
                        modifier = modifier.clickable {
                            lectureType.value = "대학탐방프로그램"
                        },
                        lectureType = stringResource(id = R.string.university_visit_program)
                    )
                    Spacer(modifier = modifier.height(28.dp))

                    Text(
                        text = stringResource(id = R.string.lecture_line),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Row {
                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.electrical_electronic)
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.machine)
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.car)
                        )
                    }

                    Spacer(modifier = modifier.height(16.dp))

                    Row {
                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.biochemical_engineering)
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.beauty)
                        )
                    }

                    Spacer(modifier = modifier.height(16.dp))

                    Row {
                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.medical_health)
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.drone)
                        )
                    }

                    Spacer(modifier = modifier.height(28.dp))

                    Text(
                        text = stringResource(id = R.string.application_start_date),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Picker(
                            modifier = modifier.weight(1f),
                            text = stringResource(id = R.string.application_start_date)
                        )

                        Spacer(modifier = modifier.width(8.dp))


                        Picker(
                            modifier = modifier.weight(1f),
                            text = stringResource(id = R.string.start_time)
                        )

                    }

                    Spacer(modifier = modifier.height(28.dp))

                    Text(
                        text = stringResource(id = R.string.application_deadline_date),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Picker(
                            modifier = modifier.weight(1f),
                            text = stringResource(id = R.string.application_deadline_date)
                        )

                        Spacer(modifier = modifier.width(8.dp))

                        Picker(
                            modifier = modifier.weight(1f),
                            text = stringResource(id = R.string.deadeline_time)
                        )
                    }

                    Spacer(modifier = modifier.height(28.dp))

                    Text(
                        text = stringResource(id = R.string.start_semester_date),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Picker(
                            modifier = modifier.weight(1f),
                            text = stringResource(id = R.string.start_semester_date)
                        )

                        Spacer(modifier = modifier.width(8.dp))

                        Picker(
                            modifier = modifier.weight(1f),
                            text = stringResource(id = R.string.start_semester_time)
                        )
                    }

                    Spacer(modifier = modifier.height(28.dp))

                    if (lectureType.value == "상호학점인정교욱과정") {
                        Column {
                            Text(
                                text = stringResource(id = R.string.credits_awarded),
                                color = colors.BLACK,
                                style = type.bodyLarge,
                                modifier = modifier.padding(bottom = 8.dp)
                            )

                            Row(
                                modifier = modifier.fillMaxWidth()
                            ) {
                                Picker(
                                    modifier = modifier.weight(1f),
                                    text = stringResource(id = R.string.select_credits_awarded)
                                )

                                Spacer(modifier = modifier.width(8.dp))

                            }
                            Spacer(modifier = modifier.height(28.dp))

                        }
                    } else if (lectureType.value == "대학탐방프로그램") {
                        Text(
                            text = stringResource(id = R.string.professor_in_charge),
                            color = colors.BLACK,
                            style = type.bodyLarge,
                            modifier = modifier.padding(bottom = 8.dp)
                        )

                    }

                    Text(
                        text = stringResource(id = R.string.maximum_number_students),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        DefaultTextField(
                            modifier = modifier.fillMaxWidth(),
                            placeholder = stringResource(id = R.string.enter_maximum_number_students),
                            errorText = "Incorrect", // 에러 텍스트는 임의의 값임
                            onValueChange = {},
                            onClickButton = {},
                            isNumberOnly = true,
                            isError = false,
                            isLinked = false,
                            isDisabled = false,
                            isReadOnly = false,
                            isReverseTrailingIcon = false,
                            onClick = {}
                        )
                    }

                    Spacer(modifier = modifier.height(165.dp))
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(color = colors.WHITE)
                ) {
                    BitgoeulButton(
                        text = stringResource(id = R.string.apply),
                        modifier = modifier
                            .padding(bottom = 14.dp, top = 16.dp)
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .height(52.dp)
                            .padding(horizontal = 24.dp),
                    ) {
                    }
                }
            }
        }
    }
}

@Composable
fun Picker(
    modifier: Modifier,
    text: String,
) {
    BitgoeulAndroidTheme { colors, type ->
        Row(
            modifier = modifier
                .background(
                    color = colors.G9,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .clickable {

                },
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = text,
                color = colors.G2,
                style = type.bodySmall,
            )

            PickerArrowIcon(isSelected = false)
        }
    }
}

@Preview
@Composable
fun LectureDetailSettingScreenPre() {
    LectureDetailSettingScreen()
}