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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import com.msg.design_system.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.bottomsheet.TimerBottomSheet
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.icon.CloseIcon
import com.msg.design_system.component.picker.Picker
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.lecture.component.LectureAttendanceDatePicker
import com.msg.lecture.component.LectureSettingTag
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.request.lecture.LectureDates

@Composable
fun LectureDetailSettingRoute(
    onCloseClicked: () -> Unit,
    onApplyClicked: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel()
) {
    LectureDetailSettingScreen(
        onCloseClicked = onCloseClicked,
        onApplyClicked = onApplyClicked
    )
}

@Composable
fun LectureDetailSettingScreen(
    onCloseClicked: () -> Unit,
    onApplyClicked: () -> Unit,
    savedCreditPoint: Int,
    savedLectureDates: List<LectureDates>,
    modifier: Modifier = Modifier,
) {
    val lectureType = remember { mutableStateOf("대학탐방프로그램") }
    val scrollState = rememberScrollState()
    val isTimeBottomSheetVisible = remember { mutableStateOf(false) }

    // TODO : 이거 스크린 내부 내용이 너무 많아서 컴포넌트화 해도 좀 더럽고 헷갈리는데 최대한 고쳐야함 ex. StackKnowledge V2 처럼 뭉탱이?로 컴포넌트화 해야하나?
    BitgoeulAndroidTheme { colors, type ->
        Surface(
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
                Spacer(modifier = modifier.height(18.dp))

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
                    Spacer(modifier = modifier.height(18.dp))

                    Text(
                        text = stringResource(id = R.string.semester_attended),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Row {
                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.first_year_second_semester)
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.second_year_first_semester)
                        )
                    }

                    Spacer(modifier = modifier.height(16.dp))

                    Row {
                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.second_year_second_semester)
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.third_year_first_semester)
                        )
                    }
                    Spacer(modifier = modifier.height(26.dp))

                    Text(
                        text = stringResource(id = R.string.lecture_division),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Row {
                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.car_industry)
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.energy_industry)
                        )

                        Spacer(modifier = modifier.width(16.dp))
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
                            lectureType = stringResource(id = R.string.ai_convergence_composite)
                        )
                    }

                    Spacer(modifier = modifier.height(16.dp))

                    LectureSettingTag(
                        modifier = modifier,
                        lectureType = stringResource(id = R.string.culture_industry)
                    )

                    Spacer(modifier = modifier.height(18.dp))

                    Text(
                        text = stringResource(id = R.string.credit),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.one_point)
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier,
                            lectureType = stringResource(id = R.string.two_point)
                        )
                    }

                    Spacer(modifier = modifier.height(18.dp))

                    Text(
                        text = stringResource(id = R.string.lecture_series),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Picker(
                        modifier = modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.select_lecture)
                    )

                    Spacer(modifier = modifier.height(18.dp))

                    Text(
                        text = stringResource(id = R.string.division),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Picker(
                        modifier = modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.select_division)
                    )

                    Spacer(modifier = modifier.height(18.dp))

                    Text(
                        text = stringResource(id = R.string.professor_in_charge),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Picker(
                        modifier = modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.select_professor_in_charge)
                    )

                    Spacer(modifier = modifier.height(18.dp))

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
                            modifier = modifier
                                .weight(1f)
                                .clickable {
                                    isTimeBottomSheetVisible.value = !isTimeBottomSheetVisible.value
                                },
                            text = stringResource(id = R.string.start_time)
                        )

                    }

                    Spacer(modifier = modifier.height(18.dp))

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
                            modifier = modifier
                                .weight(1f)
                                .clickable {
                                    isTimeBottomSheetVisible.value = !isTimeBottomSheetVisible.value
                                },
                            text = stringResource(id = R.string.deadeline_time)
                        )
                    }

                    Spacer(modifier = modifier.height(18.dp))

                    Text(
                        text = stringResource(id = R.string.lecture_attendance_date),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    LectureAttendanceDatePicker(
                        modifier = modifier.fillMaxWidth()
                    )


                    Text(
                        text = stringResource(id = R.string.maximum_number_students),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    DefaultTextField(
                        modifier = modifier
                            .padding(bottom = 128.dp)
                            .fillMaxWidth(),
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

            TimerBottomSheet(
                onQuit = { isTimeBottomSheetVisible.value = false },
                isVisible = isTimeBottomSheetVisible.value
            ) {

            }
        }
    }
}


@Preview
@Composable
fun LectureDetailSettingScreenPre() {
    LectureDetailSettingScreen(
        onCloseClicked = {},
        onApplyClicked = {}
    )
}