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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import com.msg.lecture.component.LectureDetailSettingSearchDialog
import com.msg.lecture.component.LectureSettingTag
import com.msg.lecture.viewmodel.LectureViewModel
import com.msg.model.remote.enumdatatype.Division
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.enumdatatype.Semester
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import java.util.UUID

@Composable
fun LectureDetailSettingRoute(
    onCloseClicked: () -> Unit,
    onApplyClicked: () -> Unit,
    onSearchResultItemCLick: () -> Unit,
    onSearchProfessorClicked: () -> Unit,
    onSearchLineClicked: () -> Unit,
    onSearchDepartmentClicked: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel()
) {
    LectureDetailSettingScreen(
        onCloseClicked = onCloseClicked,
        onApplyClicked = { lectureType, semester, division, department, line, userId, credit, startTime, startDate, endTime, completeDate, maxRegistereduser ->
            viewModel.lectureType.value = lectureType
            viewModel.semester.value = semester
            viewModel.division.value = division
            viewModel.department.value = department
            viewModel.line.value = line
            viewModel.userId.value = userId
            viewModel.credit.value = credit
            viewModel.completeDate.value = completeDate
            viewModel.startTime.value = startTime
            viewModel.startDate.value = startDate
            viewModel.endTime.value = endTime
            viewModel.maxRegisteredUser.value = maxRegistereduser
            onApplyClicked()
        },
        onSearchProfessorClicked = { keyword ->
            onSearchProfessorClicked()
            viewModel.searchProfessor(keyword = keyword)
        },
        onSearchLineClicked = { keyword, division ->
            onSearchLineClicked()
            viewModel.searchLine(keyword = keyword, division = division)
        },
        onSearchDepartmentClicked = { keyword ->
            onSearchDepartmentClicked()
            viewModel.searchDepartment(keyword = keyword)
        },
        onSearchResultItemCLick = { userId ->
            onSearchResultItemCLick()
            viewModel.userId.value = userId
        },
        savedCreditPoint = viewModel.credit.value,
        savedStartTime = viewModel.startTime.value,
        savedEndTime = viewModel.endTime.value,
        savedCompleteDate = viewModel.completeDate.value,
        savedStartDate = viewModel.startDate.value,
        savedEndDate = viewModel.endDate.value,
        savedMaxRegisteredUser = viewModel.maxRegisteredUser.value,
        savedSemester = viewModel.semester.value,
        savedDivision = viewModel.division.value,
        savedDepartment = viewModel.department.value,
        savedLine = viewModel.line.value,
        savedUserId = viewModel.userId.value,
        savedLectureType = viewModel.lectureType.value
    )
}

@Composable
fun LectureDetailSettingScreen(
    onCloseClicked: () -> Unit,
    onApplyClicked: (LectureType, Semester, Division, String, String, UUID, Int, LocalTime?, LocalDateTime?, LocalTime?, LocalDate?, Int) -> Unit,
    onSearchProfessorClicked: (String) -> Unit,
    onSearchLineClicked: (String, Division) -> Unit,
    onSearchDepartmentClicked: (String) -> Unit,
    onSearchResultItemCLick: (UUID) -> Unit,
    savedLectureType: LectureType,
    savedSemester: Semester,
    savedDivision: Division,
    savedDepartment: String,
    savedLine: String,
    savedUserId: UUID,
    savedCreditPoint: Int,
    savedStartDate: LocalDateTime?,
    savedEndDate: LocalDateTime?,
    savedCompleteDate: LocalDate?,
    savedStartTime: LocalTime?,
    savedEndTime: LocalTime?,
    savedMaxRegisteredUser: Int,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val isTimeBottomSheetVisible = remember { mutableStateOf(false) }
    val isSearchDialogVisible = remember { mutableStateOf(false) }
    val isClickedPickerType = remember { mutableStateListOf(false, false, false) }
    val isLectureCategoryTagSelected = remember{ mutableStateOf("0") }
    val isLectureSemesterAttendedTagSelected = remember{ mutableStateOf("0") }
    val isLectureDivisionTagSelected = remember{ mutableStateOf("0") }
    val isLectureCreditTagSelected = remember{ mutableStateOf("0") }

    val lectureType = remember { mutableStateOf(savedLectureType) }
    val division = remember { mutableStateOf(savedDivision) }
    val department = remember { mutableStateOf(savedDepartment) }
    val line = remember { mutableStateOf(savedLine) }
    val userId = remember { mutableStateOf(savedUserId) }
    val credit = remember { mutableIntStateOf(savedCreditPoint) }
    val semester = remember { mutableStateOf(savedSemester) }
    val startTime = remember { mutableStateOf(savedStartTime) }
    val startDate = remember { mutableStateOf(savedStartDate) }
    val endTime = remember { mutableStateOf(savedEndTime) }
    val endDate = remember { mutableStateOf(savedEndDate) }
    val completeDate = remember { mutableStateOf(savedCompleteDate) }
    val maxRegisteredUser = remember { mutableIntStateOf(savedMaxRegisteredUser) }

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

                    CloseIcon(
                        modifier = modifier.clickable {
                            onCloseClicked()
                        }
                    )

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
                            lectureType.value = LectureType.MUTUAL_CREDIT_RECOGNITION_PROGRAM
                        },
                        lectureType = stringResource(id = R.string.mutual_credit_recognition_curriculum),
                        isSelected = isLectureCategoryTagSelected.value == "0",
                        onClick = { isLectureCategoryTagSelected.value = "0" }
                    )

                    Spacer(modifier = modifier.height(16.dp))

                    LectureSettingTag(
                        modifier = modifier.clickable {
                            lectureType.value = LectureType.UNIVERSITY_EXPLORATION_PROGRAM
                        },
                        lectureType = stringResource(id = R.string.university_visit_program),
                        isSelected = isLectureCategoryTagSelected.value == "1",
                        onClick = { isLectureCategoryTagSelected.value = "1" }
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
                            modifier = modifier.clickable {
                                semester.value = Semester.FIRST_YEAR_FALL_SEMESTER
                            },
                            lectureType = stringResource(id = R.string.first_year_second_semester),
                            isSelected = isLectureSemesterAttendedTagSelected.value == "0",
                            onClick = { isLectureSemesterAttendedTagSelected.value = "0" }
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier.clickable {
                                semester.value = Semester.SECOND_YEAR_SPRING_SEMESTER
                            },
                            lectureType = stringResource(id = R.string.second_year_first_semester),
                            isSelected = isLectureSemesterAttendedTagSelected.value == "1",
                            onClick = { isLectureSemesterAttendedTagSelected.value = "1" }
                        )
                    }

                    Spacer(modifier = modifier.height(16.dp))

                    Row {
                        LectureSettingTag(
                            modifier = modifier.clickable {
                                semester.value = Semester.SECOND_YEAR_FALL_SEMESTER
                            },
                            lectureType = stringResource(id = R.string.second_year_second_semester),
                            isSelected = isLectureSemesterAttendedTagSelected.value == "2",
                            onClick = { isLectureSemesterAttendedTagSelected.value = "2" }
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier.clickable {
                                semester.value = Semester.THIRD_YEAR_SPRING_SEMESTER
                            },
                            lectureType = stringResource(id = R.string.third_year_first_semester),
                            isSelected = isLectureSemesterAttendedTagSelected.value == "3",
                            onClick = { isLectureSemesterAttendedTagSelected.value = "3" }
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
                            modifier = modifier.clickable {
                                division.value = Division.AUTOMOBILE_INDUSTRY
                            },
                            lectureType = stringResource(id = R.string.car_industry),
                            isSelected = isLectureDivisionTagSelected.value == "0",
                            onClick = { isLectureDivisionTagSelected.value = "0" }
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier.clickable {
                                division.value = Division.ENERGY_INDUSTRY
                            },
                            lectureType = stringResource(id = R.string.energy_industry),
                            isSelected = isLectureDivisionTagSelected.value == "1",
                            onClick = { isLectureDivisionTagSelected.value = "1" }
                        )

                        Spacer(modifier = modifier.width(16.dp))
                    }

                    Spacer(modifier = modifier.height(16.dp))

                    Row {
                        LectureSettingTag(
                            modifier = modifier.clickable {
                                division.value = Division.MEDICAL_HEALTHCARE
                            },
                            lectureType = stringResource(id = R.string.medical_health),
                            isSelected = isLectureDivisionTagSelected.value == "2",
                            onClick = { isLectureDivisionTagSelected.value = "2" }
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier.clickable {
                                division.value = Division.AI_CONVERGENCE_AI
                            },
                            lectureType = stringResource(id = R.string.ai_convergence_composite),
                            isSelected = isLectureDivisionTagSelected.value == "3",
                            onClick = { isLectureDivisionTagSelected.value = "3" }
                        )
                    }

                    Spacer(modifier = modifier.height(16.dp))

                    LectureSettingTag(
                        modifier = modifier.clickable {
                            division.value = Division.CULTURAL_INDUSTRY
                        },
                        lectureType = stringResource(id = R.string.culture_industry),
                        isSelected = isLectureDivisionTagSelected.value == "4",
                        onClick = { isLectureDivisionTagSelected.value = "4" }
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
                            modifier = modifier.clickable {
                                credit.value = 1
                            },
                            lectureType = stringResource(id = R.string.one_point),
                            isSelected = isLectureCreditTagSelected.value == "0",
                            onClick = { isLectureCreditTagSelected.value = "0" }
                        )

                        Spacer(modifier = modifier.width(16.dp))

                        LectureSettingTag(
                            modifier = modifier.clickable {
                                credit.value = 2
                            },
                            lectureType = stringResource(id = R.string.two_point),
                            isSelected = isLectureCreditTagSelected.value == "1",
                            onClick = { isLectureCreditTagSelected.value = "1" }
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
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable { isClickedPickerType[0] = !isClickedPickerType[0] },
                        text = line.value.ifEmpty { stringResource(id = R.string.select_lecture) }
                    )

                    Spacer(modifier = modifier.height(18.dp))

                    Text(
                        text = stringResource(id = R.string.department),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Picker(
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                isSearchDialogVisible.value = !isSearchDialogVisible.value
                                isClickedPickerType[1] = !isClickedPickerType[1]
                            },
                        text = stringResource(id = R.string.select_department)
                    )

                    Spacer(modifier = modifier.height(18.dp))

                    Text(
                        text = stringResource(id = R.string.professor_in_charge),
                        color = colors.BLACK,
                        style = type.bodyLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    Picker(
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable { isClickedPickerType[2] = !isClickedPickerType[2] },
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
                        errorText = "Incorrect Format", // 에러 텍스트는 임의의 값임
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
                        onApplyClicked(
                            lectureType.value,
                            semester.value,
                            division.value,
                            department.value,
                            line.value,
                            userId.value,
                            credit.value,
                            startTime.value,
                            startDate.value,
                            endTime.value,
                            completeDate.value,
                            maxRegisteredUser.value
                        )
                    }
                }
            }

            TimerBottomSheet(
                onQuit = { isTimeBottomSheetVisible.value = false },
                isVisible = isTimeBottomSheetVisible.value
            ) {

            }

            LectureDetailSettingSearchDialog(
                isVisible = isSearchDialogVisible.value,
                text = when (isClickedPickerType.indexOf(true)) {
                    0 -> stringResource(id = R.string.select_lecture)
                    1 -> stringResource(id = R.string.select_department)
                    2 -> stringResource(id = R.string.select_professor_in_charge)
                    else -> ""
                },
                searchPlaceHolder = when (isClickedPickerType.indexOf(true)) {
                    0 -> stringResource(id = R.string.lecture_series_placeholder)
                    1 -> stringResource(id = R.string.department_placeholder)
                    3 -> stringResource(id = R.string.professor_in_charge_placeholder)
                    else -> ""
                },
                searchButtonClick = { keyword, division ->
                    when (isClickedPickerType.indexOf(true)) {
                        0 -> onSearchLineClicked(keyword, division)
                        1 -> onSearchDepartmentClicked(keyword)
                        2 -> onSearchProfessorClicked(keyword)
                        else -> {}
                    }
                },
                isDepartment = when (isClickedPickerType.indexOf(true)) {
                    1 -> true
                    else -> false
                },
                onResultListClick = onSearchResultItemCLick
            )
        }
    }
}

@Preview
@Composable
fun LectureDetailSettingScreenPre() {
//    LectureDetailSettingScreen(
//        onCloseClicked = {},
//        onApplyClicked = {}
//    )
}